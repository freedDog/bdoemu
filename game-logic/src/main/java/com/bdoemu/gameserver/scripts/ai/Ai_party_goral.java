package com.bdoemu.gameserver.scripts.ai;

import com.bdoemu.commons.thread.ThreadPool;
import com.bdoemu.commons.utils.Rnd;
import com.bdoemu.gameserver.model.ai.deprecated.*;
import com.bdoemu.gameserver.model.creature.Creature;
import com.bdoemu.gameserver.model.actions.enums.*;
import com.bdoemu.gameserver.model.chat.enums.EChatNoticeType;
import com.bdoemu.gameserver.model.misc.enums.ETradeCommerceType;
import com.bdoemu.gameserver.model.weather.enums.EWeatherFactorType;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author H1X4
 */

@SuppressWarnings("all")
@IAIName("party_goral")
public class Ai_party_goral extends CreatureAI {
	public Ai_party_goral(Creature actor, Map<Long, Integer> aiVariables) {
		super(actor, aiVariables);
	}

	protected void InitialState(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xAE5FEAC2L /*InitialState*/);
		setVariable(0xE5BD13F2L /*_Degree*/, 0);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, 0);
		setVariable(0x22A52166L /*_RandomMoveCount*/, 0);
		setVariable(0xBB86C5A3L /*_BattleMode*/, 0);
		setVariable(0xA89E8217L /*_PartyType*/, getVariable(0x3E0D1FFFL /*PartyType*/));
		setVariable(0xF95B9CBEL /*_PatrolType*/, getVariable(0x4F3534F4L /*PatrolType*/));
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1000));
	}

	protected void Damage_KnockBack(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0xBF725BC4L /*Damage_KnockBack*/);
		doAction(3633065904L /*DAMAGE_KNOCKBACK*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 1000));
	}

	protected void Damage_Stun(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x3FB3341CL /*Damage_Stun*/);
		doAction(1092723167L /*DAMAGE_STUN*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 3000));
	}

	protected void Damage_KnockDown(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0x69E1FC3AL /*Damage_KnockDown*/);
		doAction(840787941L /*DAMAGE_KNOCKDOWN*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 5000));
	}

	protected void Damage_Bound(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x119675D3L /*Damage_Bound*/);
		doAction(1109738762L /*DAMAGE_BOUND*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 5000));
	}

	protected void Damage_Capture(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x5374AB60L /*Damage_Capture*/);
		doAction(3486436380L /*DAMAGE_CAPTURE*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 5000));
	}

	protected void Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x866C7489L /*Wait*/);
		setVariable(0xBB86C5A3L /*_BattleMode*/, 0);
		if (findTarget(EAIFindTargetType.Monster, EAIFindType.normal, false, object -> getDistanceToTarget(object, false) >= 0 && getDistanceToTarget(object, false) <= 1500 && getTargetHp(object) > 0 && getTargetCharacterKey(object) == 20005)) {
			if (changeState(state -> Escape(blendTime)))
				return;
		}
		if (findTarget(EAIFindTargetType.Enemy, EAIFindType.normal, false, object -> getDistanceToTarget(object, false) >= 0 && getDistanceToTarget(object, false) <= 1500 && getTargetHp(object) > 0)) {
			if (changeState(state -> SearchEnemy(blendTime)))
				return;
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 0) {
			if(getCallCount() == 5) {
				if (changeState(state -> WalkRandom(blendTime)))
					return;
			}
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1 && getVariable(0x22A52166L /*_RandomMoveCount*/) <= 2) {
			if(getCallCount() == 3) {
				if (changeState(state -> WalkRandom(blendTime)))
					return;
			}
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1 && getVariable(0xF95B9CBEL /*_PatrolType*/) == 1 && getVariable(0x22A52166L /*_RandomMoveCount*/) > 2) {
			if(getCallCount() == 3) {
				if (changeState(state -> Patrol(blendTime)))
					return;
			}
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1 && target != null && getDistanceToTarget(target) < 3000 && target != null && getTargetCharacterKey(target) == 563) {
			createParty(1, 1);
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1000 + Rnd.get(-500,500)));
	}

	protected void WalkRandom(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0xD5712181L /*WalkRandom*/);
		setVariable(0x22A52166L /*_RandomMoveCount*/, getVariable(0x22A52166L /*_RandomMoveCount*/) + 1);
		if (findTarget(EAIFindTargetType.Monster, EAIFindType.normal, false, object -> getDistanceToTarget(object, false) >= 0 && getDistanceToTarget(object, false) <= 1500 && getTargetHp(object) > 0 && getTargetCharacterKey(object) == 20005)) {
			if (changeState(state -> Escape(blendTime)))
				return;
		}
		if (findTarget(EAIFindTargetType.Enemy, EAIFindType.normal, false, object -> getDistanceToTarget(object, false) >= 0 && getDistanceToTarget(object, false) <= 1500 && getTargetHp(object) > 0)) {
			if (changeState(state -> SearchEnemy(blendTime)))
				return;
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1 && target != null && getDistanceToTarget(target) < 3000 && target != null && getTargetCharacterKey(target) == 563) {
			createParty(1, 1);
		}
		doAction(633942026L /*MOVE_WALK*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.Random, 0, 0, 0, 250, 500, true, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> Wait(blendTime), 1000)));
	}

	protected void Patrol(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x22CBEE62L /*Patrol*/);
		setVariable(0x22A52166L /*_RandomMoveCount*/, 0);
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1) {
			getObjects(EAIFindTargetType.Child, object -> true).forEach(consumer -> consumer.getAi().HandleFollowMe(getActor(), null));
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1 && target != null && getDistanceToTarget(target) < 3000 && target != null && getTargetCharacterKey(target) == 563) {
			createParty(1, 1);
		}
		doAction(3326922924L /*MOVE_CHASER*/, blendTime, onDoActionEnd -> moveToWaypoint("monster_patrol", "goral_1 goral_10 goral_25 goral_30", ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> Wait(blendTime), 1000)));
	}

	protected void SearchEnemy(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x43FC3DF7L /*SearchEnemy*/);
		setVariable(0xBB86C5A3L /*_BattleMode*/, 1);
		setVariable(0x22A52166L /*_RandomMoveCount*/, 0);
		doAction(11967395L /*SEARCH_ENEMY*/, blendTime, onDoActionEnd -> scheduleState(state -> BattleReady(blendTime), 1000));
	}

	protected void BattleReady(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x83D74061L /*BattleReady*/);
		if (isParty()) {
			setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		}
		if (target != null && getDistanceToTarget(target) < 700) {
			if (changeState(state -> Escape(blendTime)))
				return;
		}
		if (findTarget(EAIFindTargetType.Enemy, EAIFindType.normal, false, object -> getDistanceToTarget(object, false) >= 0 && getDistanceToTarget(object, false) <= 700)) {
			if (changeState(state -> Escape(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 2300) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if (target != null && getTargetHp(target) == 0) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if (isParty() && getVariable(0xCBEEF8C7L /*_ownerDistance*/) >= 2000 && getVariable(0xA89E8217L /*_PartyType*/) == 0) {
			if (changeState(state -> Party_BattleMoveToParent(blendTime)))
				return;
		}
		doAction(2820327238L /*BATTLE_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> BattleReady(blendTime), 500 + Rnd.get(-300,300)));
	}

	protected void Escape(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0xDCE8DF7DL /*Escape*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if (getVariable(0xA89E8217L /*_PartyType*/) == 1) {
			getObjects(EAIFindTargetType.Child, object -> true).forEach(consumer -> consumer.getAi().HandleFollowMeBattle(getActor(), null));
		}
		if (target != null && getDistanceToTarget(target) > 1500) {
			if (changeState(state -> BattleReady(blendTime)))
				return;
		}
		doAction(3326922924L /*MOVE_CHASER*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape(blendTime), 1500)));
	}

	protected void Battle_Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x71F28994L /*Battle_Wait*/);
		if (target != null && getDistanceToTarget(target) > 300) {
			if (changeState(state -> ChaseEnemy(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) <= 300) {
			if(Rnd.getChance(30)) {
				if (changeState(state -> Attack_Normal(blendTime)))
					return;
			}
		}
		if (target != null && getTargetHp(target) == 0) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		doAction(2820327238L /*BATTLE_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Battle_Wait(blendTime), 1000));
	}

	protected void ChaseEnemy(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.chase);
		setState(0x75E647ACL /*ChaseEnemy*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if(getCallCount() == 30) {
			if (changeState(state -> Party_MoveToParent(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) < 200) {
			if(Rnd.getChance(40)) {
				if (changeState(state -> Attack_Normal(blendTime)))
					return;
			}
		}
		if (target != null && getDistanceToTarget(target) < 150) {
			if (changeState(state -> Battle_Wait(blendTime)))
				return;
		}
		doAction(3208508703L /*CHASER_RUN*/, blendTime, onDoActionEnd -> chase(getVariable(0xD2BFE684L /*TargetCycle*/), () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseEnemy(blendTime), 100)));
	}

	protected void Attack_Normal(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0xA730A939L /*Attack_Normal*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		doAction(31162842L /*BATTLE_ATTACK1*/, blendTime, onDoActionEnd -> changeState(state -> Battle_Wait(blendTime)));
	}

	protected void Party_MoveToParent(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x133E56C1L /*Party_MoveToParent*/);
		setVariable(0x22A52166L /*_RandomMoveCount*/, 0);
		doAction(3326922924L /*MOVE_CHASER*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPath, 0, 0, 0, 150, 300, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> Wait(blendTime), 1000)));
	}

	protected void Party_BattleMoveToParent(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0xD6B14CE9L /*Party_BattleMoveToParent*/);
		setVariable(0x22A52166L /*_RandomMoveCount*/, 0);
		doAction(3326922924L /*MOVE_CHASER*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPath, 0, 0, 0, 150, 300, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> BattleReady(blendTime), 1000)));
	}

	@Override
	public EAiHandlerResult HandleTakeDamage(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		getObjects(EAIFindTargetType.Monster, object -> getDistanceToTarget(object) < 2000).forEach(consumer -> consumer.getAi().HandleHelpMe_Goral(getActor(), null));
		if (getVariable(0xBB86C5A3L /*_BattleMode*/) == 0) {
			if (changeState(state -> SearchEnemy(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0xBB86C5A3L /*_BattleMode*/) == 1) {
			if (changeState(state -> ChaseEnemy(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleKnockBack(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_KnockBack(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleStuned(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_Stun(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleKnockDown(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_KnockDown(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleBound(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_Bound(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleStun(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_Stun(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleCapture(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_Capture(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandlePartyReleased(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (getVariable(0xBB86C5A3L /*_BattleMode*/) == 0) {
			if (changeState(state -> SearchEnemy(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandlePartyInvited(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (getVariable(0xA89E8217L /*_PartyType*/) == 0) {
			if (changeState(state -> Wait(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleHelpMe_Goral(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		getActor().getAggroList().addCreature(sender.getAggroList().getTarget());
		if (getVariable(0xBB86C5A3L /*_BattleMode*/) == 0) {
			if (changeState(state -> SearchEnemy(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0xBB86C5A3L /*_BattleMode*/) == 1) {
			if (changeState(state -> ChaseEnemy(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleFollowMeBattle(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (getVariable(0xA89E8217L /*_PartyType*/) == 0) {
			if (changeState(state -> Party_BattleMoveToParent(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleFollowMe(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (getVariable(0xA89E8217L /*_PartyType*/) == 0) {
			if (changeState(state -> Party_MoveToParent(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}
}
