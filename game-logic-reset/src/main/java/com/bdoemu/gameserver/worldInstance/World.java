package com.bdoemu.gameserver.worldInstance;

import com.bdoemu.MainServer;
import com.bdoemu.commons.model.enums.EServerBusyState;
import com.bdoemu.commons.network.SendablePacket;
import com.bdoemu.core.configs.ServerConfig;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.Gate;
import com.bdoemu.gameserver.model.creature.enums.ECharKind;
import com.bdoemu.gameserver.model.creature.enums.ERemoveActorType;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.houses.HouseHold;
import com.bdoemu.gameserver.model.world.Location;
import com.bdoemu.gameserver.model.world.storages.ObjectStorage;
import com.bdoemu.gameserver.service.ShutdownService;
import com.bdoemu.gameserver.model.creature.servant.Servant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName World
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/7 20:40
 * VERSION 1.0
 */

@StartupComponent("World")
public class World {

    private static final Logger log = LoggerFactory.getLogger(World.class);
    /**所有对象存储*/
    private static final ObjectStorage objects = new ObjectStorage();
    /**所有玩家  key 名字  value 玩家*/
    private static final Map<String, Player> playersByName = new HashMap<>();
    /**所有玩家  key 家族名  value 玩家*/
    private static final Map<String, Player> playersByFamilyName = new HashMap<>();
    /**所有玩家  key objectId value 玩家*/
    private static final Map<Long, Player> playersByObject = new HashMap<>();
    /**所有玩家  key accountId  value 玩家*/
    private static final Map<Long, Player> playersByAccount = new HashMap<>();
    /**世界地图*/
    private static WorldMap worldMap = new WorldMap();
    /**服务器繁忙状态*/
    private EServerBusyState currentBusyState;
    public World() {
        this.currentBusyState = EServerBusyState.INSPECTION;
    }

    public static World getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 生产对象
     * @param object
     * @param isNewSpawn
     * @param isRespawn
     * @return
     */
    public boolean spawn(final Creature object, final boolean isNewSpawn, final boolean isRespawn) {
        if (World.objects.contains(object)) {
            World.log.warn("Object already spawned: {}", (Object) object.toString());
            return false;
        }
        object.onSpawn();
        final boolean success = World.worldMap.spawn(object, isNewSpawn, isRespawn);
        if (success) {
            World.objects.add(object);
            if (object.isPlayer()) {
                final Player player = (Player) object;
                World.playersByName.put(player.getName(), player);
                World.playersByFamilyName.put(player.getFamily(), player);
                World.playersByObject.put(player.getObjectId(), player);
                World.playersByAccount.put(player.getAccountId(), player);
                this.onPlayerCountChanged();
            }
            object.initAi();
        }
        return success;
    }

    /**
     * 获取世界地图信息
     * @return
     */
    public WorldMap getWorldMap() {
        return World.worldMap;
    }

    /**
     * 销毁
     * @param object 对象
     * @param type 类型
     * @return
     */
    public boolean deSpawn(final Creature object, final ERemoveActorType type) {
        synchronized (World.objects) {
            if (!object.isSpawned()) {
                return false;
            }
            World.objects.remove(object);
            if (object.isPlayer()) {
                final Player player = (Player) object;
                World.playersByName.remove(player.getName());
                World.playersByObject.remove(player.getObjectId());
                World.playersByFamilyName.remove(player.getFamily());
                World.playersByAccount.remove(player.getAccountId());
                this.onPlayerCountChanged();
            }
            object.onDespawn();
            World.worldMap.despawn(object, type);
            object.setSpawned(false);
            return true;
        }
    }

    /***
     * 传送
     * @param object 对象
     * @param loc 目标位置
     * @return
     */
    public boolean teleport(final Creature object, final Location loc) {
        synchronized (World.objects) {
            if (!object.isSpawned()) {
                return false;
            }
            if (object.isPlayer()) {
                final Player player = (Player) object;
                final Servant servant = player.getCurrentVehicle();
                if (servant != null) {
                    if (servant.getOwner() == player) {
                        servant.getGameStats().getHp().kill(servant);
                        servant.unMountAll();
                    } else {
                        servant.unMount(player);
                    }
                }
            }
            if (World.worldMap.despawn(object, ERemoveActorType.Teleport)) {
                object.setSpawned(false);
                object.getLocation().setXYZ(loc.getX(), loc.getY(), loc.getZ());
                return World.worldMap.spawn(object, true, false);
            }
        }
        return true;
    }

    /**
     * 发送世界消息
     * @param sp
     */
    public void broadcastWorldPacket(final SendablePacket<GameClient> sp) {
        for (final Player player : this.getPlayers()) {
            player.sendPacket(sp);
        }
    }

    public Creature getObjectById(final int objectId) {
        return World.objects.getObject(objectId);
    }

    public Collection<Player> getPlayers() {
        return World.objects.getObjectsByType(ECharKind.Player);
    }

    public Player getPlayer(final String name) {
        return World.playersByName.get(name);
    }

    public Player getPlayerByFamily(final String name) {
        return World.playersByFamilyName.get(name);
    }

    public Player getPlayerByAccount(final long accountId) {
        return World.playersByAccount.get(accountId);
    }

    public Player getPlayer(final long objectId) {
        return World.playersByObject.get(objectId);
    }

    private <T extends Creature> T getObject(final ECharKind kind, final int gameObjectId) {
        return World.objects.getObjectByType(kind, gameObjectId);
    }

    public <T extends Creature> Collection<T> getObjects(final ECharKind kind) {
        return World.objects.getObjectsByType(kind);
    }

    public <T extends Creature> Collection<T> getObjects(final Class<T> clazz) {
        return this.getObjects(ECharKind.forClass(clazz));
    }

    public Collection<Creature> getObjects() {
        return World.objects.getObjects();
    }

    public Player getPlayer(final int gameObjectId) {
        return this.getObject(ECharKind.Player, gameObjectId);
    }

    public Servant getServant(final int gameObjectId) {
        return this.getObject(ECharKind.Vehicle, gameObjectId);
    }

    public Collection<Gate> getGates() {
        return this.getObjects(ECharKind.Alterego);
    }

    public Collection<HouseHold> getTents() {
        return this.getObjects(ECharKind.Household);
    }

    private void onPlayerCountChanged() {
//        final EServerBusyState busyState = this.getBusyState();
//        if (this.currentBusyState != busyState) {
//            this.broadcastWorldPacket(new SMChangeServerBusyState(this.currentBusyState, busyState));
//            this.currentBusyState = busyState;
//            MainServer.getRmi().setBusyState(this.currentBusyState);
//        }
    }

    public EServerBusyState getBusyState() {
        if (ShutdownService.getInstance().isShutdownInProgress()) {
            return EServerBusyState.INSPECTION;
        }
        final int playersCount = this.getPlayers().size();
        if (playersCount <= ServerConfig.SERVER_BUSY_STATE_LIMIT_SMOOTH) {
            return EServerBusyState.SMOOTH;
        }
        if (playersCount <= ServerConfig.SERVER_BUSY_STATE_LIMIT_BUSY) {
            return EServerBusyState.BUSY;
        }
        if (playersCount <= ServerConfig.SERVER_BUSY_STATE_LIMIT_VERY_CROWDED) {
            return EServerBusyState.VERY_CROWDED;
        }
        return EServerBusyState.VERY_CROWDED;
    }

    private static class Holder {
        static final World INSTANCE = new World();
    }
}
