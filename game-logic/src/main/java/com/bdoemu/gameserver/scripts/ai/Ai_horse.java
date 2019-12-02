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
@IAIName("horse")
public class Ai_horse extends CreatureAI {
	public Ai_horse(Creature actor, Map<Long, Integer> aiVariables) {
		super(actor, aiVariables);
	}

	protected void InitialState(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xAE5FEAC2L /*InitialState*/);
		setVariable(0x9617E562L /*isWater*/, 0);
		setVariable(0xAB16882DL /*isUnderWater*/, 0);
		setVariable(0x71D35262L /*isWaterUnder*/, 0);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, 0);
		setVariable(0x42A6EC2DL /*_isRunning*/, 0);
		setVariable(0x41E6DE54L /*_isTamingRunCount*/, 0);
		setVariable(0x940229E0L /*_isState*/, 0);
		setVariable(0x27462B34L /*_isTamingGameStart*/, 0);
		setVariable(0x444DFF4EL /*_isFindPathCompleted*/, 0);
		setVariable(0xFDC61BCCL /*_FailFindPathCount*/, 0);
		setVariable(0x5997DB32L /*_FailFindTamingPathCount*/, 0);
		setVariable(0x5F29F441L /*_MoveType*/, 0);
		if (isPartyMember()) {
			setVariable(0x940229E0L /*_isState*/, 2);
		}
		setVariable(0x5F29F441L /*_MoveType*/, getVariable(0x42DBAAE6L /*DummyType*/));
		if (false) {
			setVariable(0x940229E0L /*_isState*/, 3);
		}
		if (getVariable(0x940229E0L /*_isState*/) == 3) {
			if (changeState(state -> Wait_House(blendTime)))
				return;
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1000));
	}

	protected void Riding_Wait_Logic(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.none);
		setState(0x4264729AL /*Riding_Wait_Logic*/);
		if (getVariable(0x940229E0L /*_isState*/) == 0) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		changeState(state -> Riding_Wait(blendTime));
	}

	protected void Riding_Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x5C23B8A0L /*Riding_Wait*/);
		doAction(225192220L /*PARKING_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> TerminateState(blendTime), 100));
	}

	protected void TerminateState(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.none);
		setState(0xF74207F6L /*TerminateState*/);
		// Prevent recursion;
	}

	protected void revival(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x59ADD975L /*revival*/);
		doAction(1727911018L /*REVIVAL*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1000));
	}

	protected void Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x866C7489L /*Wait*/);
		if (getVariable(0x940229E0L /*_isState*/) == 0) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		if (getVariable(0x940229E0L /*_isState*/) == 1) {
			if (changeState(state -> ChaseOwner_Taming(blendTime)))
				return;
		}
		if (getVariable(0x940229E0L /*_isState*/) == 3) {
			if (changeState(state -> Wait_House(blendTime)))
				return;
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1000));
	}

	protected void UnderWater_Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x60B31F66L /*UnderWater_Wait*/);
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> UnderWater_Wait(blendTime), 1000));
	}

	protected void Wait_taming_rope(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xC219A44FL /*Wait_taming_rope*/);
		doAction(3614979032L /*Taming_Start*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_taming_rope2(blendTime), 1000));
	}

	protected void Wait_taming_rope2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x9F4EB4A7L /*Wait_taming_rope2*/);
		if(getCallCount() == 150) {
			if (changeState(state -> Wait_taming_fail(blendTime)))
				return;
		}
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 1) {
			if (changeState(state -> Wait_taming_fail(blendTime)))
				return;
		}
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 2) {
			if (changeState(state -> Wait_taming_fail(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 250) {
			if(Rnd.getChance(30)) {
				if (changeState(state -> Wait_taming_rope_check(blendTime)))
					return;
			}
		}
		doAction(974625300L /*WAIT_Avoid*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_taming_rope2(blendTime), 1000));
	}

	protected void Wait_taming_rope_check(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x746EBC40L /*Wait_taming_rope_check*/);
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 0) {
			if(getCallCount() == 4) {
				if (changeState(state -> Wait_taming_fail(blendTime)))
					return;
			}
		}
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 1) {
			if(getCallCount() == 4) {
				if (changeState(state -> Wait_taming_rope_check2(blendTime)))
					return;
			}
		}
		doAction(165953471L /*ROAR_Taming*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_taming_rope_check(blendTime), 1000));
	}

	protected void Wait_taming_rope_check2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x67CF8538L /*Wait_taming_rope_check2*/);
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 0) {
			if(getCallCount() == 13) {
				if (changeState(state -> Wait_taming_fail(blendTime)))
					return;
			}
		}
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 1) {
			if(getCallCount() == 13) {
				if (changeState(state -> Wait_taming_fail(blendTime)))
					return;
			}
		}
		if (getVariable(0x27462B34L /*_isTamingGameStart*/) == 2) {
			if (changeState(state -> Wait_taming_rope_check3(blendTime)))
				return;
		}
		doAction(2932989077L /*Taming_Shake*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_taming_rope_check2(blendTime), 1000));
	}

	protected void Wait_taming_rope_check3(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xA20B222DL /*Wait_taming_rope_check3*/);
		setVariable(0x27462B34L /*_isTamingGameStart*/, 0);
		doAction(974625300L /*WAIT_Avoid*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_taming_rope2(blendTime), 1000));
	}

	protected void Wait_taming_fail(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0xF812D6DCL /*Wait_taming_fail*/);
		resetTamingInfo();
		setVariable(0x27462B34L /*_isTamingGameStart*/, 0);
		doAction(2864939577L /*ATTACK_TAMING_FAIL*/, blendTime, onDoActionEnd -> changeState(state -> Escape_Lv5_taming(blendTime)));
	}

	protected void Wait_wild(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xECA33C7FL /*Wait_wild*/);
		setVariable(0x27462B34L /*_isTamingGameStart*/, 0);
		if (getDistanceToSpawn() > 3000) {
			if (changeState(state -> Move_Return(0.3)))
				return;
		}
		if (getVariable(0x5F29F441L /*_MoveType*/) == 0) {
			if(getCallCount() == 4) {
				if (changeState(state -> Move_Random(blendTime)))
					return;
			}
		}
		if (getVariable(0x940229E0L /*_isState*/) == 1) {
			if (changeState(state -> ChaseOwner_Taming(blendTime)))
				return;
		}
		if (getVariable(0x5F29F441L /*_MoveType*/) == 0) {
			if (findTarget(EAIFindTargetType.Enemy, EAIFindType.normal, false, object -> getDistanceToTarget(object) < 1000)) {
				if (changeState(state -> Escape_Lv5_taming(0.3)))
					return;
			}
		}
		doAction(3598373039L /*WAIT_Taming*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_wild(blendTime), 500));
	}

	protected void Move_Random(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x8377635AL /*Move_Random*/);
		if (findTarget(EAIFindTargetType.Enemy, EAIFindType.normal, false, object -> getDistanceToTarget(object) < 1000)) {
			if (changeState(state -> Escape_Lv5_taming(0.3)))
				return;
		}
		if (getDistanceToSpawn() > 3000) {
			if (changeState(state -> Move_Return(0.3)))
				return;
		}
		doAction(2695636746L /*MOVE_LV1_START_Taming*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.Random, 0, 0, 0, 200, 800, true, ENaviType.ground, () -> {
			setVariable(0x444DFF4EL /*_isFindPathCompleted*/, isFindPathCompleted());
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0 && getVariable(0xFDC61BCCL /*_FailFindPathCount*/) >= 3) {
				if (changeState(state -> Move_Return(blendTime)))
					return true;
			}
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0) {
				if (changeState(state -> FailFindPath_Logic(blendTime)))
					return true;
			}
			return false;
		}, onExit -> scheduleState(state -> Wait_wild(blendTime), 1000)));
	}

	protected void FailFindPath_Logic(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.none);
		setState(0x43584A24L /*FailFindPath_Logic*/);
		setVariable(0xFDC61BCCL /*_FailFindPathCount*/, getVariable(0xFDC61BCCL /*_FailFindPathCount*/) + 1);
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> changeState(state -> Wait(blendTime)));
	}

	protected void FailFindPath(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x80CB99B0L /*FailFindPath*/);
		setVariable(0xFDC61BCCL /*_FailFindPathCount*/, 0);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0x940229E0L /*_isState*/) == 0) {
			if (changeState(state -> FailFindPath_wild(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) <= 50000 && !isRiderExist()) {
			doTeleport(EAIMoveDestType.OwnerPosition, 0, 0, 1, 1);
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 1500));
	}

	protected void FailFindPath_wild(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.none);
		setState(0xCC1DD65EL /*FailFindPath_wild*/);
		setVariable(0xFDC61BCCL /*_FailFindPathCount*/, 0);
		doTeleport(EAIMoveDestType.Random, 0, 0, 1, 1);
		changeState(state -> Wait(blendTime));
	}

	protected void Move_Return(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.return_);
		setState(0xD61E465EL /*Move_Return*/);
		doAction(1256653841L /*MOVE_LV2_ING_Taming*/, blendTime, onDoActionEnd -> recoveryAndReturn(() -> {
			setVariable(0x444DFF4EL /*_isFindPathCompleted*/, isFindPathCompleted());
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0) {
				if (changeState(state -> FailFindPath(blendTime)))
					return true;
			}
			return false;
		}, onExit -> scheduleState(state -> Wait_wild(blendTime), 1000)));
	}

	protected void ChaseOwner_Taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x806FDD59L /*ChaseOwner_Taming*/);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 350) {
			if (changeState(state -> ChaseOwner_TamingMove1(blendTime)))
				return;
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> ChaseOwner_Taming(blendTime), 1000));
	}

	protected void ChaseOwner_TamingMove1(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x2A4726C9L /*ChaseOwner_TamingMove1*/);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 800) {
			if (changeState(state -> ChaseOwner_TamingMove2(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 200) {
			if (changeState(state -> ChaseOwner_Taming(blendTime)))
				return;
		}
		doAction(2695636746L /*MOVE_LV1_START_Taming*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_TamingMove1(blendTime), 500)));
	}

	protected void ChaseOwner_TamingMove2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x369ADA28L /*ChaseOwner_TamingMove2*/);
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 450) {
			if (changeState(state -> ChaseOwner_TamingMove1(blendTime)))
				return;
		}
		doAction(1256653841L /*MOVE_LV2_ING_Taming*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_TamingMove2(blendTime), 500)));
	}

	protected void FailFindPath_Taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.none);
		setState(0x56BCB8F7L /*FailFindPath_Taming*/);
		setVariable(0x5997DB32L /*_FailFindTamingPathCount*/, getVariable(0x5997DB32L /*_FailFindTamingPathCount*/) + 1);
		if (getVariable(0x5997DB32L /*_FailFindTamingPathCount*/) >= 5) {
			if (changeState(state -> TamingOwnerPosition_Teleport(blendTime)))
				return;
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> changeState(state -> ChaseOwner_TamingMove2(blendTime)));
	}

	protected void TamingOwnerPosition_Teleport(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xDEF56453L /*TamingOwnerPosition_Teleport*/);
		setVariable(0x5997DB32L /*_FailFindTamingPathCount*/, 0);
		if (!isRiderExist()) {
			doTeleport(EAIMoveDestType.OwnerPosition, 200, 0, 1, 1);
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> ChaseOwner_Taming(blendTime), 1500));
	}

	protected void OwnerPosition_Teleport(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xE9E106CL /*OwnerPosition_Teleport*/);
		if (!isRiderExist()) {
			doTeleport(EAIMoveDestType.OwnerPosition, 200, 0, 1, 1);
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> MovingStop_Lv1(blendTime), 1500));
	}

	protected void ChaseOwner_Stance(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x24359F08L /*ChaseOwner_Stance*/);
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 350) {
			if (changeState(state -> ChaseOwner_MoveLv1(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 150) {
			if (changeState(state -> MovingStop_Lv1(blendTime)))
				return;
		}
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> ChaseOwner_Stance(blendTime), 1000));
	}

	protected void ChaseOwner_MoveLv1(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x643D5C4CL /*ChaseOwner_MoveLv1*/);
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 800) {
			if (changeState(state -> ChaseOwner_MoveLv2(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 150) {
			if (changeState(state -> MovingStop_Lv1(blendTime)))
				return;
		}
		if(getCallCount() == 10) {
			if (changeState(state -> MovingStop_Lv1(blendTime)))
				return;
		}
		doAction(3283123083L /*MOVE_LV1_ING*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_MoveLv1(blendTime), 500)));
	}

	protected void ChaseOwner_MoveLv2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0xF1003A81L /*ChaseOwner_MoveLv2*/);
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 450) {
			if (changeState(state -> ChaseOwner_MoveLv1(blendTime)))
				return;
		}
		if (getSelfInventoryWeight() < 40 && getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 1100) {
			if (changeState(state -> ChaseOwner_MoveLv4(blendTime)))
				return;
		}
		if (getSelfInventoryWeight() < 80 && getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 1100) {
			if (changeState(state -> ChaseOwner_MoveLv3(blendTime)))
				return;
		}
		doAction(1793358191L /*MOVE_LV2_ING*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_MoveLv2(blendTime), 300)));
	}

	protected void ChaseOwner_MoveLv3(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0xD22A0FD1L /*ChaseOwner_MoveLv3*/);
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 450) {
			if (changeState(state -> ChaseOwner_Stop(blendTime)))
				return;
		}
		if (getSelfInventoryWeight() < 40 && getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 1100) {
			if (changeState(state -> ChaseOwner_MoveLv4(blendTime)))
				return;
		}
		if(getCallCount() == 10) {
			if (changeState(state -> ChaseOwner_Stop(blendTime)))
				return;
		}
		doAction(3167670346L /*MOVE_LV3_HALF*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_MoveLv2(blendTime), 300)));
	}

	protected void ChaseOwner_MoveLv4(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x6A17EDF0L /*ChaseOwner_MoveLv4*/);
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> OwnerPosition_Teleport(blendTime)))
				return;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) < 800) {
			if (changeState(state -> ChaseOwner_Stop(blendTime)))
				return;
		}
		doAction(2609545190L /*MOVE_LV4_HALF*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.OwnerPosition, 0, 0, 0, 0, 0, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> ChaseOwner_MoveLv4(blendTime), 100)));
	}

	protected void ChaseOwner_Stop(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0xC795B6F3L /*ChaseOwner_Stop*/);
		doAction(799326965L /*MOVE_SLOW_STOP_SHORT*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void MovingStop_Back(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x41B9C8BCL /*MovingStop_Back*/);
		doAction(4014228058L /*AI_MOVE_LV1_ING*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void MovingStop_Lv1(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x13E9B7F1L /*MovingStop_Lv1*/);
		doAction(4014228058L /*AI_MOVE_LV1_ING*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void MovingStop_Lv2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x5828E0F4L /*MovingStop_Lv2*/);
		doAction(4213640064L /*AI_MOVE_LV2_ING*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void MovingStop_Lv4(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x41DEFE21L /*MovingStop_Lv4*/);
		doAction(799326965L /*MOVE_SLOW_STOP_SHORT*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void MovingStop_Lv4_taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0xFE137796L /*MovingStop_Lv4_taming*/);
		clearAggro(true);
		doAction(2185007639L /*MOVE_SLOW_STOP_SHORT_Taming*/, blendTime, onDoActionEnd -> changeState(state -> Wait_wild(blendTime)));
	}

	protected void ParkingHorse_Wait2(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x9FEF6E8EL /*ParkingHorse_Wait2*/);
		setVariable(0x42A6EC2DL /*_isRunning*/, 0);
		if (getVariable(0x940229E0L /*_isState*/) == 1) {
			if (changeState(state -> ChaseOwner_Taming(blendTime)))
				return;
		}
		doAction(225192220L /*PARKING_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> ParkingHorse_Wait3(blendTime), 8000));
	}

	protected void ParkingHorse_Wait3(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x2DAC69C5L /*ParkingHorse_Wait3*/);
		setVariable(0x42A6EC2DL /*_isRunning*/, 0);
		if (getVariable(0x940229E0L /*_isState*/) == 1) {
			if (changeState(state -> ChaseOwner_Taming(blendTime)))
				return;
		}
		doAction(4106691328L /*PARKING_WAIT_NINEGRADE*/, blendTime, onDoActionEnd -> changeState(state -> ParkingHorse_Wait2(blendTime)));
	}

	protected void Parking_Off(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x3EC5707BL /*Parking_Off*/);
		clearAggro(true);
		doAction(2514775444L /*WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait(blendTime), 500));
	}

	protected void Attack_Front(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0xB3D1FCA5L /*Attack_Front*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		doAction(1510053325L /*ATTACK_FRONT_KICK*/, blendTime, onDoActionEnd -> changeState(state -> Wait(blendTime)));
	}

	protected void Attack_Back(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.action);
		setState(0x9F591AEEL /*Attack_Back*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		doAction(3812218278L /*ATTACK_BACK_KICK*/, blendTime, onDoActionEnd -> changeState(state -> Wait(blendTime)));
	}

	protected void Escape_Lv4(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0x184316BBL /*Escape_Lv4*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 1200) {
			if (changeState(state -> MovingStop_Lv4(blendTime)))
				return;
		}
		doAction(2609545190L /*MOVE_LV4_HALF*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape_Lv4(blendTime), 500)));
	}

	protected void Escape_Lv1(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0xB21D7758L /*Escape_Lv1*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 500) {
			if (changeState(state -> Wait(blendTime)))
				return;
		}
		doAction(3283123083L /*MOVE_LV1_ING*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape_Lv1(blendTime), 500)));
	}

	protected void Escape_Lv5_taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0xE5866066L /*Escape_Lv5_taming*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		if (getDistanceToSpawn() > 3500) {
			if (changeState(state -> Move_Return(0.3)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 1200) {
			if (changeState(state -> MovingStop_Lv4_taming(blendTime)))
				return;
		}
		doAction(1332774868L /*MOVE_LV4_HALF_Taming*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape_Lv4_taming(blendTime), 500)));
	}

	protected void Escape_Lv4_taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0x72B674FAL /*Escape_Lv4_taming*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		if (getDistanceToSpawn() > 3500) {
			if (changeState(state -> Move_Return(0.3)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 1200) {
			if (changeState(state -> MovingStop_Lv4_taming(blendTime)))
				return;
		}
		doAction(1332774868L /*MOVE_LV4_HALF_Taming*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape_Lv4_taming(blendTime), 500)));
	}

	protected void Escape_Lv1_taming(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.escape);
		setState(0x398C9E0CL /*Escape_Lv1_taming*/);
		if (isTargetLost()) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		if (getDistanceToSpawn() > 3000) {
			if (changeState(state -> Move_Return(0.3)))
				return;
		}
		if (target != null && getDistanceToTarget(target) > 500) {
			if (changeState(state -> Wait_wild(blendTime)))
				return;
		}
		doAction(2573099270L /*MOVE_LV1_ING_Taming*/, blendTime, onDoActionEnd -> escape(2500, () -> {
			return false;
		}, onExit -> scheduleState(state -> Escape_Lv1_taming(blendTime), 500)));
	}

	protected void Damage_SkillFail_KnockDown(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0x2B3484E8L /*Damage_SkillFail_KnockDown*/);
		doAction(1872990688L /*KNOCKDOWN*/, blendTime, onDoActionEnd -> scheduleState(state -> Riding_Wait_Logic(blendTime), 1000));
	}

	protected void Damage_SkillFail_KnockDown_At_Moving(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0xD0BFE1E9L /*Damage_SkillFail_KnockDown_At_Moving*/);
		doAction(2575501718L /*KNOCKDOWN_AT_MOVING*/, blendTime, onDoActionEnd -> scheduleState(state -> Riding_Wait_Logic(blendTime), 1000));
	}

	protected void Damage_KnockDown(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0x69E1FC3AL /*Damage_KnockDown*/);
		doAction(1872990688L /*KNOCKDOWN*/, blendTime, onDoActionEnd -> scheduleState(state -> Damage_KnockDown_Wait(blendTime), 1000));
	}

	protected void Damage_KnockDown_At_Moving(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0x25EE997L /*Damage_KnockDown_At_Moving*/);
		doAction(2575501718L /*KNOCKDOWN_AT_MOVING*/, blendTime, onDoActionEnd -> scheduleState(state -> Damage_KnockDown_Wait(blendTime), 1000));
	}

	protected void Damage_KnockDown_Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0x19C9327AL /*Damage_KnockDown_Wait*/);
		doAction(3089467598L /*KNOCKDOWN_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> Riding_Wait_Logic(blendTime), 7000));
	}

	protected void Damage_KnockBack(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0xBF725BC4L /*Damage_KnockBack*/);
		doAction(4059115659L /*KNOCKBACK*/, blendTime, onDoActionEnd -> scheduleState(state -> Riding_Wait_Logic(blendTime), 1000));
	}

	protected void Damage_KnockBack_At_Moving(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0xB669FB51L /*Damage_KnockBack_At_Moving*/);
		doAction(2658109441L /*KNOCKBACK_AT_MOVING*/, blendTime, onDoActionEnd -> scheduleState(state -> Riding_Wait_Logic(blendTime), 1000));
	}

	protected void Damage_Fear(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0xBF1D8728L /*Damage_Fear*/);
		doAction(1002473142L /*DAMAGE_FEAR*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.Random, 0, 0, 0, 0, 1000, false, ENaviType.ground, () -> {
			return false;
		}, onExit -> scheduleState(state -> Damage_Fear(blendTime), 100)));
	}

	protected void NaturalDeath_Wait(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xF95AB35L /*NaturalDeath_Wait*/);
		doAction(336830910L /*NATURALDEATH_WAIT*/, blendTime, onDoActionEnd -> scheduleState(state -> NaturalDeath(blendTime), 2000));
	}

	protected void NaturalDeath(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.dead);
		setState(0xFC98619EL /*NaturalDeath*/);
		doAction(3574206083L /*NATURALDEATH*/, blendTime, onDoActionEnd -> scheduleState(state -> NaturalDeath(blendTime), 2000));
	}

	protected void UnderWater_KnockDown(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.knockback);
		setState(0xBF7E038EL /*UnderWater_KnockDown*/);
		doAction(1872990688L /*KNOCKDOWN*/, blendTime, onDoActionEnd -> scheduleState(state -> UnderWater_Wait(blendTime), 1000));
	}

	protected void Wait_House(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xCAB2CF45L /*Wait_House*/);
		if(Rnd.getChance(25)) {
			if (changeState(state -> Walk_Random_House(blendTime)))
				return;
		}
		doAction(229697768L /*WAIT_House*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_House(blendTime), 500));
	}

	protected void Walk_Random_House(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.walk);
		setState(0x26903310L /*Walk_Random_House*/);
		doAction(4114471916L /*MOVE_LV1_START_House*/, blendTime, onDoActionEnd -> move(EAIMoveDestType.Random, 0, 0, 0, 100, 500, true, ENaviType.ground, () -> {
			setVariable(0x444DFF4EL /*_isFindPathCompleted*/, isFindPathCompleted());
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0 && getVariable(0xFDC61BCCL /*_FailFindPathCount*/) >= 3) {
				if (changeState(state -> FailFindPath_House(0.3)))
					return true;
			}
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0) {
				if (changeState(state -> FailFindPath_House_Logic(0.3)))
					return true;
			}
			return false;
		}, onExit -> scheduleState(state -> Wait_House(blendTime), 1000)));
	}

	protected void Move_Return_House(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.return_);
		setState(0x1ED7CB14L /*Move_Return_House*/);
		doAction(4114471916L /*MOVE_LV1_START_House*/, blendTime, onDoActionEnd -> recoveryAndReturn(() -> {
			setVariable(0x444DFF4EL /*_isFindPathCompleted*/, isFindPathCompleted());
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0 && getVariable(0xFDC61BCCL /*_FailFindPathCount*/) >= 3) {
				if (changeState(state -> FailFindPath_House(0.3)))
					return true;
			}
			if (getVariable(0x444DFF4EL /*_isFindPathCompleted*/) == 0) {
				if (changeState(state -> FailFindPath_House_Logic(0.3)))
					return true;
			}
			return false;
		}, onExit -> scheduleState(state -> Wait_House(blendTime), 1000)));
	}

	protected void FailFindPath_House_Logic(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xB5462542L /*FailFindPath_House_Logic*/);
		setVariable(0xFDC61BCCL /*_FailFindPathCount*/, getVariable(0xFDC61BCCL /*_FailFindPathCount*/) + 1);
		if (getVariable(0xFDC61BCCL /*_FailFindPathCount*/) > 3 && getVariable(0x870CD143L /*_IsPartyMember*/) == 0) {
			if (changeState(state -> FailFindPath_House(0.3)))
				return;
		}
		doAction(229697768L /*WAIT_House*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_House(blendTime), 1500));
	}

	protected void FailFindPath_House(double blendTime) {
		Creature target = getTarget();
		setBehavior(EAIBehavior.idle);
		setState(0xF6FA9865L /*FailFindPath_House*/);
		doTeleport(EAIMoveDestType.Random, 0, 0, 1, 1);
		doAction(229697768L /*WAIT_House*/, blendTime, onDoActionEnd -> scheduleState(state -> Wait_House(blendTime), 1500));
	}

	@Override
	public EAiHandlerResult HandleTargetInMyArea(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (target != null && getTargetHp(target) > 0 && (getState() == 0xECA33C7FL /*Wait_wild*/ || getState() == 0x8377635AL /*Move_Random*/) && getVariable(0x5F29F441L /*_MoveType*/) == 0) {
			getActor().getAggroList().addCreature(sender.getAggroList().getTarget());
			if (changeState(state -> Escape_Lv5_taming(0.3)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRevive(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> revival(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTakeDamage(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (target != null && checkBuff(target, 13) && getState() != 0x25EE997L /*Damage_KnockDown_At_Moving*/ && getState() != 0x69E1FC3AL /*Damage_KnockDown*/ && getVariable(0x42A6EC2DL /*_isRunning*/) == 1) {
			if (changeState(state -> Damage_KnockDown_At_Moving(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (target != null && checkBuff(target, 13) && getState() != 0x25EE997L /*Damage_KnockDown_At_Moving*/ && getState() != 0x69E1FC3AL /*Damage_KnockDown*/ && getVariable(0x42A6EC2DL /*_isRunning*/) == 0) {
			if (changeState(state -> Damage_KnockDown(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0x940229E0L /*_isState*/) == 0 && !isRiderExist()) {
			if(Rnd.getChance(50)) {
				if (changeState(state -> Escape_Lv4_taming(0.1)))
					return EAiHandlerResult.CHANGE_STATE;
			}
		}
		if (getVariable(0x940229E0L /*_isState*/) == 0 && !isRiderExist()) {
			if(Rnd.getChance(50)) {
				if (changeState(state -> Escape_Lv1_taming(0.1)))
					return EAiHandlerResult.CHANGE_STATE;
			}
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleKnockDown(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleKnockBack(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleFeared(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleFearReleased(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleWhistle(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0xCBEEF8C7L /*_ownerDistance*/, getDistanceToOwner());
		setVariable(0x940229E0L /*_isState*/, 2);
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) <= 7500 && !isRiderExist()) {
			if (changeState(state -> ChaseOwner_Stance(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0xCBEEF8C7L /*_ownerDistance*/) > 7500 && getVariable(0xCBEEF8C7L /*_ownerDistance*/) <= 50000 && !isRiderExist()) {
			if (changeState(state -> FailFindPath(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandlerNaturalDeath(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> NaturalDeath_Wait(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleParkingHorse(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> ParkingHorse_Wait2(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleOnResetAI(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0xAB16882DL /*isUnderWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water_ground") ? 1 : 0));
		setVariable(0x71D35262L /*isWaterUnder*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("under_water") ? 1 : 0));
		setVariable(0x9617E562L /*isWater*/, (getCurrentPos_NaviType()==getNaviTypeStringToEnum("water") ? 1 : 0));
		if (getVariable(0xAB16882DL /*isUnderWater*/) == 1) {
			if (changeState(state -> UnderWater_KnockDown(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0x71D35262L /*isWaterUnder*/) == 1) {
			if (changeState(state -> UnderWater_KnockDown(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0x9617E562L /*isWater*/) == 1) {
			if (changeState(state -> UnderWater_KnockDown(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (changeState(state -> Riding_Wait(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleCallSummon(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x940229E0L /*_isState*/, 2);
		if (changeState(state -> ChaseOwner_Stance(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleParkingOff(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Parking_Off(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleSkillLearnFailFront(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_SkillFail_KnockDown_At_Moving(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleSkillLearnFailBack(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Damage_SkillFail_KnockDown(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideOff_Wait(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> ParkingHorse_Wait2(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideOff_Back(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> MovingStop_Back(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideOff_Lv1(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> MovingStop_Lv1(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideOff_Lv2(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> MovingStop_Lv2(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideOff_Lv4(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> MovingStop_Lv4(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleCheckStatus_Run(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x42A6EC2DL /*_isRunning*/, 1);
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleCheckStatus_Idle(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x42A6EC2DL /*_isRunning*/, 0);
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleMoveInWater(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (isRiderExist()) {
			if (changeState(state -> UnderWater_KnockDown(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleRideEnd(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (getVariable(0x42A6EC2DL /*_isRunning*/) == 1 && getState() != 0xD0BFE1E9L /*Damage_SkillFail_KnockDown_At_Moving*/ && getState() != 0x2B3484E8L /*Damage_SkillFail_KnockDown*/ && getState() != 0x25EE997L /*Damage_KnockDown_At_Moving*/ && getState() != 0x69E1FC3AL /*Damage_KnockDown*/ && getState() != 0xB669FB51L /*Damage_KnockBack_At_Moving*/ && getState() != 0xBF725BC4L /*Damage_KnockBack*/ && getState() != 0xBF7E038EL /*UnderWater_KnockDown*/) {
			if (changeState(state -> MovingStop_Lv2(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getVariable(0x940229E0L /*_isState*/) != 2 && getState() != 0xD0BFE1E9L /*Damage_SkillFail_KnockDown_At_Moving*/ && getState() != 0x2B3484E8L /*Damage_SkillFail_KnockDown*/ && getState() != 0x25EE997L /*Damage_KnockDown_At_Moving*/ && getState() != 0x69E1FC3AL /*Damage_KnockDown*/ && getState() != 0xB669FB51L /*Damage_KnockBack_At_Moving*/ && getState() != 0xBF725BC4L /*Damage_KnockBack*/ && getState() != 0xBF7E038EL /*UnderWater_KnockDown*/) {
			if (changeState(state -> Wait(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		if (getState() != 0xD0BFE1E9L /*Damage_SkillFail_KnockDown_At_Moving*/ && getState() != 0x2B3484E8L /*Damage_SkillFail_KnockDown*/ && getState() != 0x25EE997L /*Damage_KnockDown_At_Moving*/ && getState() != 0x69E1FC3AL /*Damage_KnockDown*/ && getState() != 0xB669FB51L /*Damage_KnockBack_At_Moving*/ && getState() != 0xBF725BC4L /*Damage_KnockBack*/ && getState() != 0xBF7E038EL /*UnderWater_KnockDown*/) {
			if (changeState(state -> ParkingHorse_Wait2(0.1)))
				return EAiHandlerResult.CHANGE_STATE;
		}
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTryTaming(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Wait_taming_rope(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTamingStep1(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x27462B34L /*_isTamingGameStart*/, 1);
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTamingStep2(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x27462B34L /*_isTamingGameStart*/, 2);
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTaming(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x940229E0L /*_isState*/, 1);
		if (changeState(state -> ChaseOwner_Taming(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleFailTaming(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		if (changeState(state -> Wait_taming_fail(0.1)))
			return EAiHandlerResult.CHANGE_STATE;
		return EAiHandlerResult.BYPASS;
	}

	@Override
	public EAiHandlerResult HandleTamingSugar(Creature sender, Integer[] eventData) {
		_sender = sender;
		Creature target = sender;
		setVariable(0x41E6DE54L /*_isTamingRunCount*/, 0);
		return EAiHandlerResult.BYPASS;
	}
}
