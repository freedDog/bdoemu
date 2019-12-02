package com.bdoemu.gameserver.model.creature.player.controllers;

import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.creature.player.cooltimes.ActionRestrictionEntry;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ActionRestrictionController {
    private Player _player;
    private ConcurrentHashMap<Integer, ActionRestrictionEntry> _actionRestrictions;

    public ActionRestrictionController(Player player) {
        _actionRestrictions = new ConcurrentHashMap<>();
        _player = player;
    }

    public Player getPlayer() {
        return _player;
    }

    public boolean isActionRestricted(int actionType) {
        ActionRestrictionEntry entry = _actionRestrictions.get(actionType);
        return !(entry == null || entry.isExpired());
    }

    public boolean checkRestrictCondition(List<Integer> actionTypes) {
        for (Integer actionType : actionTypes) {
            if (isActionRestricted(actionType))
                return false;
        }

        return true;
    }

    public void restrictActions(Creature attacker, List<Integer> actionTypes, int mainActionType, int expireTime) {
        for (Integer actionType : actionTypes)
            restrictAction(attacker, actionType, expireTime, mainActionType == actionType ? 500 : 0);
    }

    public void restrictAction(Creature attacker, int actionType, int expireTime, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, expireTime + offset);

        //_player.dispatchSystemMessage("RestrictAction[Type=" + actionType + "]=" + (expireTime + offset) + " ms.", EChatType.Notice, EChatNoticeType.Normal);

        ActionRestrictionEntry entry = _actionRestrictions.get(actionType);
        if (entry == null)
            _actionRestrictions.put(actionType, new ActionRestrictionEntry(actionType, calendar.getTimeInMillis()));
        else
            entry.setExpirationTime(calendar.getTimeInMillis());
    }
}









