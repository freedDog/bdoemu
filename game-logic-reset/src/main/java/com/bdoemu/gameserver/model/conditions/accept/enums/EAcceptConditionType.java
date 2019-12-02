package com.bdoemu.gameserver.model.conditions.accept.enums;

import com.bdoemu.gameserver.model.conditions.accept.CheckBuffACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckClassACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckCollectThemeACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckConnectedNodeACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckCreationIndexACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckEquipItemACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckEquipSlotAndCashCondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckEscortACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckExploreACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckHaveTitleCondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckPartyACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckPcRoomACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckPeriodACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckPeriodbyGMTACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckPositionACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckQuestACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckRegionTypeACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckSiegeACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckTerritoryACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckTimeACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckUseItemToTargetACondition;
import com.bdoemu.gameserver.model.conditions.accept.CheckWomanACondition;
import com.bdoemu.gameserver.model.conditions.accept.ClearQuestACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetExplorePointAConditon;
import com.bdoemu.gameserver.model.conditions.accept.GetIntimacyACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetInventoryFreeCountACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetItemCountACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetKnowledgeACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetLevelACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetLifeLevelACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetSkillLevelACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetTendencyACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetWeatherFactorACondition;
import com.bdoemu.gameserver.model.conditions.accept.GetWpACondition;
import com.bdoemu.gameserver.model.conditions.accept.IAcceptConditionHandler;
import com.bdoemu.gameserver.model.conditions.accept.IsContentsGroupOpenACondition;
import com.bdoemu.gameserver.model.conditions.accept.IsGuildMemberCondition;
import com.bdoemu.gameserver.model.conditions.accept.IsTitleCondition;
import com.bdoemu.gameserver.model.conditions.accept.ProgressGuildQuestACondition;
import com.bdoemu.gameserver.model.conditions.accept.ProgressQuestACondition;

import java.util.HashMap;

/**
 * @ClassName EAcceptConditionType
 * @Description  接受条件类型
 * @Author JiangBangMing
 * @Date 2019/7/8 21:25
 * VERSION 1.0
 */
public enum EAcceptConditionType {

    checkExplore {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckExploreACondition();
        }
    },
    getLifeLevel {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetLifeLevelACondition();
        }
    },
    checkWoman {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckWomanACondition();
        }
    },
    checkClass {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckClassACondition();
        }
    },
    getIntimacy {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetIntimacyACondition();
        }
    },
    getLevel {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetLevelACondition();
        }
    },
    getKnowledge {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetKnowledgeACondition();
        }
    },
    progressQuest {
        @Override
        IAcceptConditionHandler newInstance() {
            return new ProgressQuestACondition();
        }
    },
    clearQuest {
        @Override
        IAcceptConditionHandler newInstance() {
            return new ClearQuestACondition();
        }
    },
    isGuildMember {
        @Override
        IAcceptConditionHandler newInstance() {
            return new IsGuildMemberCondition();
        }
    },
    isTitle {
        @Override
        IAcceptConditionHandler newInstance() {
            return new IsTitleCondition();
        }
    },
    checkHaveTitle {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckHaveTitleCondition();
        }
    },
    checkEquipSlotAndCash {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckEquipSlotAndCashCondition();
        }
    },
    checkTime {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckTimeACondition();
        }
    },
    getItemCount {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetItemCountACondition();
        }
    },
    checkBuff {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckBuffACondition();
        }
    },
    checkTerritory {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckTerritoryACondition();
        }
    },
    checkQuestCondition {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckQuestACondition();
        }
    },
    getSkillLevel {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetSkillLevelACondition();
        }
    },
    getTendency {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetTendencyACondition();
        }
    },
    checkEquipItem {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckEquipItemACondition();
        }
    },
    checkCollectTheme {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckCollectThemeACondition();
        }
    },
    getInventoryFreeCount {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetInventoryFreeCountACondition();
        }
    },
    getWp {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetWpACondition();
        }
    },
    getExplorePoint {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetExplorePointAConditon();
        }
    },
    getAquredExplorePoint {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetExplorePointAConditon();
        }
    },
    checkParty {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckPartyACondition();
        }
    },
    getWeatherFactor {
        @Override
        IAcceptConditionHandler newInstance() {
            return new GetWeatherFactorACondition();
        }
    },
    checkPcRoom {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckPcRoomACondition();
        }
    },
    checkConnectedNode {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckConnectedNodeACondition();
        }
    },
    checkEscort {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckEscortACondition();
        }
    },
    checkPeriod {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckPeriodACondition();
        }
    },
    isContentsGroupOpen {
        @Override
        IAcceptConditionHandler newInstance() {
            return new IsContentsGroupOpenACondition();
        }
    },
    checkCreationIndex {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckCreationIndexACondition();
        }
    },
    checkPosition {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckPositionACondition();
        }
    },
    checkRegionType {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckRegionTypeACondition();
        }
    },
    checkUseItemToTarget {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckUseItemToTargetACondition();
        }
    },
    checkPeriodbyGMT {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckPeriodbyGMTACondition();
        }
    },
    checkSiege {
        @Override
        IAcceptConditionHandler newInstance() {
            return new CheckSiegeACondition();
        }
    },
    progressGuildquest {
        @Override
        IAcceptConditionHandler newInstance() {
            return new ProgressGuildQuestACondition();
        }
    };

    private static final HashMap<String, EAcceptConditionType> map;

    static {
        map = new HashMap<>();
        for (final EAcceptConditionType type : values()) {
            EAcceptConditionType.map.put(type.name().toLowerCase(), type);
        }
    }

    public static EAcceptConditionType getConditionType(final String conditionName) {
        try {
            EAcceptConditionType.map.get(conditionName.toLowerCase());
        } catch(Exception e) {
            e.printStackTrace();
        }

        return EAcceptConditionType.map.get(conditionName.toLowerCase());
    }

    abstract IAcceptConditionHandler newInstance();

    public IAcceptConditionHandler newConditionInstance() {
        return this.newInstance();
    }
}
