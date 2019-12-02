package com.bdoemu.gameserver.model.conditions.complete.enums;

import com.bdoemu.gameserver.model.conditions.complete.CheckLevelUpCCondition;
import com.bdoemu.gameserver.model.conditions.complete.ClearMiniGameCCondition;
import com.bdoemu.gameserver.model.conditions.complete.ClearQuestCCondition;
import com.bdoemu.gameserver.model.conditions.complete.CollectItemCCondition;
import com.bdoemu.gameserver.model.conditions.complete.CollectKnowledgeCCondition;
import com.bdoemu.gameserver.model.conditions.complete.DetectionCCondition;
import com.bdoemu.gameserver.model.conditions.complete.EavesdropCCondition;
import com.bdoemu.gameserver.model.conditions.complete.ExchangeItemCCondition;
import com.bdoemu.gameserver.model.conditions.complete.ExploreCCondtion;
import com.bdoemu.gameserver.model.conditions.complete.GatherCCondition;
import com.bdoemu.gameserver.model.conditions.complete.HaveGamePointCCondition;
import com.bdoemu.gameserver.model.conditions.complete.ICompleteConditionHandler;
import com.bdoemu.gameserver.model.conditions.complete.KillDarkCCondition;
import com.bdoemu.gameserver.model.conditions.complete.KillMonsterCCondition;
import com.bdoemu.gameserver.model.conditions.complete.KillMonsterGroupCCondition;
import com.bdoemu.gameserver.model.conditions.complete.KillPlayerCCondition;
import com.bdoemu.gameserver.model.conditions.complete.LearnSkillCCondition;
import com.bdoemu.gameserver.model.conditions.complete.MeetCCondition;
import com.bdoemu.gameserver.model.conditions.complete.NpcWorkerMakingCCondition;
import com.bdoemu.gameserver.model.conditions.complete.TamingServantCCondition;
import com.bdoemu.gameserver.model.conditions.complete.TradeCCondition;
import com.bdoemu.gameserver.model.conditions.complete.UseItemCCondition;

import java.util.HashMap;

/**
 * @ClassName ECompleteConditionType
 * @Description   完成条件类型
 * @Author JiangBangMing
 * @Date 2019/7/8 21:32
 * VERSION 1.0
 */
public enum ECompleteConditionType {
    explore {
        @Override
        ICompleteConditionHandler newInstance() {
            return new ExploreCCondtion();
        }
    },
    meet {
        @Override
        ICompleteConditionHandler newInstance() {
            return new MeetCCondition();
        }
    },
    useItem {
        @Override
        ICompleteConditionHandler newInstance() {
            return new UseItemCCondition();
        }
    },
    learnSkill {
        @Override
        ICompleteConditionHandler newInstance() {
            return new LearnSkillCCondition();
        }
    },
    killMonster {
        @Override
        ICompleteConditionHandler newInstance() {
            return new KillMonsterCCondition();
        }
    },
    gatheritem {
        @Override
        ICompleteConditionHandler newInstance() {
            return new GatherCCondition();
        }
    },
    killMonsterGroup {
        @Override
        ICompleteConditionHandler newInstance() {
            return new KillMonsterGroupCCondition();
        }
    },
    exchangeItem {
        @Override
        ICompleteConditionHandler newInstance() {
            return new ExchangeItemCCondition();
        }
    },
    collectKnowledge {
        @Override
        ICompleteConditionHandler newInstance() {
            return new CollectKnowledgeCCondition();
        }
    },
    collectItem {
        @Override
        ICompleteConditionHandler newInstance() {
            return new CollectItemCCondition();
        }
    },
    clearMiniGame {
        @Override
        ICompleteConditionHandler newInstance() {
            return new ClearMiniGameCCondition();
        }
    },
    trade {
        @Override
        ICompleteConditionHandler newInstance() {
            return new TradeCCondition();
        }
    },
    npcworkermaking {
        @Override
        ICompleteConditionHandler newInstance() {
            return new NpcWorkerMakingCCondition();
        }
    },
    havegamepoint {
        @Override
        ICompleteConditionHandler newInstance() {
            return new HaveGamePointCCondition();
        }
    },
    detection {
        @Override
        ICompleteConditionHandler newInstance() {
            return new DetectionCCondition();
        }
    },
    eavesdrop {
        @Override
        ICompleteConditionHandler newInstance() {
            return new EavesdropCCondition();
        }
    },
    killPlayer {
        @Override
        ICompleteConditionHandler newInstance() {
            return new KillPlayerCCondition();
        }
    },
    killDark {
        @Override
        ICompleteConditionHandler newInstance() {
            return new KillDarkCCondition();
        }
    },
    checkLevelUp {
        @Override
        ICompleteConditionHandler newInstance() {
            return new CheckLevelUpCCondition();
        }
    },
    clearquest {
        @Override
        ICompleteConditionHandler newInstance() {
            return new ClearQuestCCondition();
        }
    },
    tamingServant {
        @Override
        ICompleteConditionHandler newInstance() {
            return new TamingServantCCondition();
        }
    };

    private static final HashMap<String, ECompleteConditionType> map;

    static {
        map = new HashMap<>();
        for (final ECompleteConditionType type : values()) {
            ECompleteConditionType.map.put(type.name().toLowerCase(), type);
        }
    }

    public static ECompleteConditionType getConditionType(final String conditionName) {
        return ECompleteConditionType.map.get(conditionName.toLowerCase());
    }

    abstract ICompleteConditionHandler newInstance();

    public ICompleteConditionHandler newConditionInstance() {
        return this.newInstance();
    }
}
