package com.bdoemu.gameserver.model.functions.enums;

import com.bdoemu.gameserver.model.functions.AskKnowledgeFunction;
import com.bdoemu.gameserver.model.functions.BuyItemByPointFunction;
import com.bdoemu.gameserver.model.functions.DetectPlayerFunction;
import com.bdoemu.gameserver.model.functions.EscortCompleteFunction;
import com.bdoemu.gameserver.model.functions.ExchangeItemFunction;
import com.bdoemu.gameserver.model.functions.ExchangeItemToGroupFunction;
import com.bdoemu.gameserver.model.functions.ExchangeSilverFunction;
import com.bdoemu.gameserver.model.functions.ExecuteHandlerFunction;
import com.bdoemu.gameserver.model.functions.ExecuteSearchFunction;
import com.bdoemu.gameserver.model.functions.IFunctionHandler;
import com.bdoemu.gameserver.model.functions.PushItemFunction;
import com.bdoemu.gameserver.model.functions.PushKnowledgeFunction;
import com.bdoemu.gameserver.model.functions.RemoveItemFunction;
import com.bdoemu.gameserver.model.functions.RestoreItemByPointFunction;
import com.bdoemu.gameserver.model.functions.ShowCutSceneFunction;

import java.util.HashMap;

/**
 * @ClassName EFunctionType
 * @Description  方法类型
 * @Author JiangBangMing
 * @Date 2019/7/9 15:26
 * VERSION 1.0
 */

public enum  EFunctionType {
    /**护送完成*/
    escortComplete {
        @Override
        IFunctionHandler newInstance() {
            return new EscortCompleteFunction();
        }
    },
    /**执行处理程序*/
    executeHandler {
        @Override
        IFunctionHandler newInstance() {
            return new ExecuteHandlerFunction();
        }
    },
    restoreItemByPoint {
        @Override
        IFunctionHandler newInstance() {
            return new RestoreItemByPointFunction();
        }
    },
    buyItemByPoint {
        @Override
        IFunctionHandler newInstance() {
            return new BuyItemByPointFunction();
        }
    },
    exchangeItemToGroup {
        @Override
        IFunctionHandler newInstance() {
            return new ExchangeItemToGroupFunction();
        }
    },
    exchangeItem {
        @Override
        IFunctionHandler newInstance() {
            return new ExchangeItemFunction();
        }
    },
    exchangeSilver {
        @Override
        IFunctionHandler newInstance() {
            return new ExchangeSilverFunction();
        }
    },
    showCutScene {
        @Override
        IFunctionHandler newInstance() {
            return new ShowCutSceneFunction();
        }
    },
    pushItem {
        @Override
        IFunctionHandler newInstance() {
            return new PushItemFunction();
        }
    },
    removeItem {
        @Override
        IFunctionHandler newInstance() {
            return new RemoveItemFunction();
        }
    },
    detectPlayer {
        @Override
        IFunctionHandler newInstance() {
            return new DetectPlayerFunction();
        }
    },
    executeSearch {
        @Override
        IFunctionHandler newInstance() {
            return new ExecuteSearchFunction();
        }
    },
    pushKnowledge {
        @Override
        IFunctionHandler newInstance() {
            return new PushKnowledgeFunction();
        }
    },
    askKnowledge {
        @Override
        IFunctionHandler newInstance() {
            return new AskKnowledgeFunction();
        }
    };

    private static final HashMap<String, EFunctionType> map;

    static {
        map = new HashMap<>();
        for (final EFunctionType type : values()) {
            EFunctionType.map.put(type.name().toLowerCase(), type);
        }
    }

    public static EFunctionType getFunctionType(final String functionName) {
        return EFunctionType.map.get(functionName.toLowerCase());
    }

    abstract IFunctionHandler newInstance();

    public IFunctionHandler newFunctionInstance() {
        return this.newInstance();
    }
}
