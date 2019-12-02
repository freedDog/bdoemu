package com.bdoemu.core.configs;

import com.bdoemu.commons.config.annotation.ConfigComments;
import com.bdoemu.commons.config.annotation.ConfigFile;
import com.bdoemu.commons.config.annotation.ConfigProperty;
import com.bdoemu.commons.model.enums.EGuildWarType;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LocalizingOptionConfig
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/24 16:44
 * VERSION 1.0
 */
@ConfigFile(name = "configs/localizingoption.properties")
public class LocalizingOptionConfig {
    @ConfigProperty(name = "AccessibleRegionGroupKey", value = "1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,31,33,34,36,37,38,39,40,41,42,43,44,45,46,51,52,53,54,55,56,58,61,62,63,64,101,102,103,104,105,106,107,108,109,110,111,112,202,203,204,205,206,207,208,209,210,211,212,213,214,215")
    public static List<Integer> ACCESSIBLE_REGION_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedConditionGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_CONDITION_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedItemGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_ITEM_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedCharacterGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_CHARACTER_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedExchangeGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_EXCHANGE_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedQuestGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_QUEST_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedMentalCardGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_MENTAL_CARD_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedTitleGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_TITLE_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "OpenedContentsGroupKey", value = "1,2,3,4,11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,39,40,41,42,43,44,45,48,50,51,52,55,56,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,77,79,81,82,84,86,89,100,901,902,903,904,905,908,998,1006,1009,1014,1018")
    public static List<Integer> OPENED_CONTENTS_GROUP_KEY = new ArrayList();

    @ConfigProperty(name = "PriceBalanceLevel", value = "0")
    public static int PRICE_BALANCE_LEVEL;

    @ConfigProperty(name = "MonsterBalanceLevel", value = "0")
    public static int MONSTER_BALANCE_LEVEL;

    @ConfigProperty(name = "AquireExperienceRate", value = "0")
    public static int AQUIRE_EXPERIENCE_RATE;

    @ConfigProperty(name = "AquireExperienceRateUsingItem", value = "0")
    public static int AQUIRE_EXPERIENCE_RATE_USING_ITEM;

    @ConfigProperty(name = "AquireExperienceRateByLifeActivity", value = "0")
    public static int AQUIRE_EXPERIENCE_RATE_BY_LIFE_ACTIVITY;

    @ConfigProperty(name = "AquireSkillExperienceRate", value = "0")
    public static int AQUIRE_SKILL_EXPERIENCE_RATE;

    @ConfigProperty(name = "AquireVehicleExperienceRate", value = "0")
    public static int AQUIRE_VEHICLE_EXPERIENCE_RATE;

    @ConfigProperty(name = "AquireItemDropRate", value = "0")
    public static int AQUIRE_ITEM_DROP_RATE;

    @ConfigProperty(name = "ContentsServiceType", value = "0")
    public static int CONTENTS_SERVICE_TYPE;

    @ConfigProperty(name = "PossibleClass", value = "0,4,8,12,16,20,21,24,25,26,28,31,27")
    public static List<Integer> POSSIBLE_CLASS = new ArrayList();
    @ConfigProperty(name = "UsePassword", value = "1")
    public static int USE_PASSWORD;
    @ConfigProperty(name = "UsePcExchange", value = "0")
    public static int USE_PC_EXCHANGE;
    @ConfigProperty(name = "PcExchangeLimit", value = "0")
    public static int PC_EXCHANGE_LIMIT;
    @ConfigProperty(name = "MaxContribute", value = "0")
    public static int MAX_CONTRIBUTE;
    @ConfigProperty(name = "CharacterMaxLevel", value = "61")
    public static int CHARACTER_MAX_LEVEL;
    @ConfigProperty(name = "CharacterExpReduceRate", value = "0")
    public static int CHARACTER_EXP_REDUCE_RATE;
    @ConfigProperty(name = "CharacterRemoveTimeCheckLevel", value = "1")
    public static int CHARACTER_REMOVE_TIME_CHECK_LEVEL;
    @ConfigProperty(name = "LowLevelCharacterRemoveTime", value = "86400")
    public static int LOW_LEVEL_CHARACTER_REMOVE_TIME;
    @ConfigProperty(name = "CharacterRemoveTime", value = "86400")
    public static int CHARACTER_REMOVE_TIME;
    @ConfigProperty(name = "NameRemoveTime", value = "86400")
    public static int NAME_REMOVE_TIME;
    @ConfigProperty(name = "CharacterRemoveExceptWorldNo", value = "91")
    public static int CHARACTER_REMOVE_EXCEPT_WORLD_NO;
    @ConfigProperty(name = "CharacterRemoveExceptDate", value = "2014-12-17 05:00:00")
    public static String CHARACTER_REMOVE_EXCEPT_DATE;
    @ConfigProperty(name = "CharacterRemoveExceptTime", value = "600")
    public static int CHARACTER_REMOVE_EXCEPT_TIME;
    @ConfigProperty(name = "CheckUniqueName", value = "0")
    public static int CHECK_UNIQUE_NAME;
    @ConfigProperty(name = "CanRegisterPearlItemOnMarket", value = "1")
    public static int CAN_REGISTER_PEARL_ITEM_ON_MARKET;
    @ConfigProperty(name = "CanRegisterCashItemToItemMarketAtP2p", value = "0")
    public static int CAN_REGISTER_CASH_ITEM_TO_ITEM_MARKET_AT_P2P;
    @ConfigProperty(name = "BasePriceCorrectionValueForCashItem", value = "1")
    public static int BASE_PRICE_CORRECTION_VALUE_FOR_CASH_ITEM;
    @ConfigProperty(name = "BiddingWaitTime", value = "0")
    public static int BIDDING_WAIT_TIME;
    @ConfigProperty(name = "BiddingTime", value = "60000")
    public static int BIDDING_TIME;
    @ConfigProperty(name = "BiddingSuccesRate", value = "0")
    public static int BIDDING_SUCCES_RATE;
    @ConfigProperty(name = "CanMakeGuild", value = "1")
    public static int CAN_MAKE_GUILD;
    @ConfigProperty(name = "VehicleGradeValue", value = "10")
    public static int VEHICLE_GRADE_VALUE;
    @ConfigProperty(name = "AdrenalinCondition", value = "0")
    public static int ADRENALIN_CONDITION;
    @ConfigProperty(name = "AwakeningLevel", value = "47")
    public static int AWAKENING_LEVEL;
    @ConfigProperty(name = "ResetAwakeningLevel", value = "49")
    public static int RESET_AWAKENING_LEVEL;
    @ConfigProperty(name = "PvPCondition", value = "0")
    public static int PVP_CONDITION;
    @ConfigProperty(name = "DefaultCharacterSlot", value = "6")
    public static int DEFAULT_CHARACTER_SLOT;
    @ConfigProperty(name = "CharacterSlotLimit", value = "12")
    public static int CHARACTER_SLOT_LIMIT;
    @ConfigProperty(name = "PreemptiveAttackTendency", value = "-10000")
    public static int PREEMPTIVE_ATTACK_TENDENCY;
    @ConfigProperty(name = "PKTendencyPenalty", value = "-200000")
    public static int PK_TENDENCY_PENALTY;
    @ConfigProperty(name = "PreemptiveStrikeKeepingTime", value = "60000")
    public static int PREEMPTIVE_STRIKE_KEEPING_TIME;
    @ConfigComments(comment = {"Guild war type", "Options: NORMAL, BOTH"})
    @ConfigProperty(name = "GuildWarType", value = "NORMAL")
    public static EGuildWarType GUILD_WAR_TYPE;
    @ConfigProperty(name = "ConsumeGuildMoneyForGuildWar", value = "1000000")
    public static int CONSUME_GUILD_MONEY_FOR_GUILD_WAR;
    @ConfigProperty(name = "PenaltyTimeForGuildWar", value = "7200")
    public static int PENALTY_TIME_FOR_GUILD_WAR;
    @ConfigProperty(name = "ConsumeGuildMoneyForGuildWarContinue", value = "300000")
    public static int CONSUME_GUILD_MONEY_FOR_GUILD_WAR_CONTINUE;
    @ConfigProperty(name = "AbsenceTImeForChangeGuildMaster", value = "2592000")
    public static int ABSENCE_TIME_FOR_CHANGE_GUILD_MASTER;
    @ConfigProperty(name = "CommissionRateForGuildDuel", value = "200000")
    public static int COMMISSION_RATE_FOR_GUILD_DUEL;
    @ConfigProperty(name = "DailyAvailableCountForGuildDuel", value = "1")
    public static int DAILY_AVAILABLE_COUNT_FOR_GUILD_DUEL;
    @ConfigProperty(name = "ProtectGuildMemberRate", value = "0")
    public static int PROTECT_GUILD_MEMBER_RATE;
    @ConfigProperty(name = "MaxProtectGuildMemberRate", value = "0")
    public static int MAX_PROTECT_GUILD_MEMBER_RATE;
    @ConfigProperty(name = "InstallableSiegeTentCount", value = "0")
    public static int INSTALLABLE_SIEGE_TENT_COUNT;
    @ConfigProperty(name = "PcRoomBuff", value = "0")
    public static int PC_ROOM_BUFF;
    @ConfigProperty(name = "PremiumPackageBuff", value = "0")
    public static int PREMIUM_PACKAGE_BUFF;
    @ConfigProperty(name = "StarterPackageBuff", value = "0")
    public static int STARTER_PACKAGE_BUFF;
    @ConfigProperty(name = "PearlPackageBuff", value = "0")
    public static int PEARL_PACKAGE_BUFF;
    @ConfigProperty(name = "OncePackageBuff", value = "0")
    public static int ONCE_PACKAGE_BUFF;
    @ConfigProperty(name = "ChannelMoveOnlySafetyArea", value = "0")
    public static int CHANNEL_MOVE_ONLY_SAFETY_AREA;
    @ConfigProperty(name = "SupportLoopNavigationGuide", value = "1")
    public static int SUPPORT_LOOP_NAVIGATION_GUIDE;
    @ConfigProperty(name = "LogOutWaitingTime", value = "10000")
    public static int LOGOUT_WAITING_TIME;
    @ConfigProperty(name = "CollectExpRate", value = "0")
    public static int COLLECT_EXP_RATE;
    @ConfigProperty(name = "CollectDropRate", value = "0")
    public static int COLLECT_DROP_RATE;
    @ConfigProperty(name = "AlchemyExpRate", value = "0")
    public static int ALCHEMY_EXP_RATE;
    @ConfigProperty(name = "AlchemyDropRate", value = "0")
    public static int ALCHEMY_DROP_RATE;
    @ConfigProperty(name = "ManufactureExpRate", value = "0")
    public static int MANUFACTURE_EXP_RATE;
    @ConfigProperty(name = "ManufactureDropRate", value = "0")
    public static int MANUFACTURE_DROP_RATE;
    @ConfigProperty(name = "FishingExpRate", value = "0")
    public static int FISHING_EXP_RATE;
    @ConfigProperty(name = "FishingDropRate", value = "0")
    public static int FISHING_DROP_RATE;
    @ConfigProperty(name = "CookExpRate", value = "0")
    public static int COOK_EXP_RATE;
    @ConfigProperty(name = "CookDropRate", value = "0")
    public static int COOK_DROP_RATE;
    @ConfigProperty(name = "HuntingExpRate", value = "0")
    public static int HUNTING_EXP_RATE;
    @ConfigProperty(name = "HuntingDropRate", value = "0")
    public static int HUNTING_DROP_RATE;
    @ConfigProperty(name = "VehicleTrainingExpRate", value = "0")
    public static int VEHICLE_TRAINING_EXP_RATE;
    @ConfigProperty(name = "VehicleTraningDropRate", value = "0")
    public static int VEHICLE_TRANING_DROP_RATE;
    @ConfigProperty(name = "TradingExpRate", value = "0")
    public static int TRADING_EXP_RATE;
    @ConfigProperty(name = "TradingDropRate", value = "0")
    public static int TRADING_DROP_RATE;
    @ConfigProperty(name = "TradeItemOriginalPriceRate", value = "0")
    public static int TRADE_ITEM_ORIGINAL_PRICE_RATE;
    @ConfigProperty(name = "CollectExpRateForQuest", value = "0")
    public static int COLLECT_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "AlchemyExpRateForQuest", value = "0")
    public static int ALCHEMY_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "ManufactureExpRateForQuest", value = "0")
    public static int MANUFACTURE_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "FishingExpRateForQuest", value = "0")
    public static int FISHING_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "CookExpRateForQuest", value = "0")
    public static int COOK_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "HuntingExpRateForQuest", value = "0")
    public static int HUNTING_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "VehicleTrainingExpRateForQuest", value = "0")
    public static int VEHICLE_TRAINING_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "TradingExpRateForQuest", value = "0")
    public static int TRADING_EXP_RATE_FOR_QUEST;
    @ConfigProperty(name = "ItemMarketRefundPercentForPcRoom", value = "0")
    public static int ITEM_MARKET_REFUND_PERCENT_FOR_PCROOM;
    @ConfigProperty(name = "ItemMarketRefundPercentForPremiumPackage", value = "300000")
    public static int ITEM_MARKET_REFUND_PERCENT_FOR_PREMIUM_PACKAGE;
    @ConfigProperty(name = "ItemMarketRefundPercentForPcRoomAndPremiumPackage", value = "0")
    public static int ITEM_MARKET_REFUND_PERCENT_FOR_PCROOM_AND_PREMIUM_PACKAGE;
    @ConfigProperty(name = "CpuBusyRate", value = "0")
    public static int CPU_BUSY_RATE;
    @ConfigProperty(name = "UseWPatLifeActivity", value = "0")
    public static int USE_WP_AT_LIFE_ACTIVITY;
    @ConfigProperty(name = "UseWPatLifeActivityManufacture", value = "0")
    public static int USE_WP_AT_LIFE_ACTIVITY_MANUFACTURE;
    @ConfigProperty(name = "NoneDecreaseWpPercentLifeLevel", value = "10000")
    public static int NONE_DECREASE_WP_PERCENT_LIFE_LEVEL;
    @ConfigProperty(name = "NoneDecreaseWpMaxPercent", value = "600000")
    public static int NONE_DECREASE_WP_MAX_PERCENT;
    @ConfigProperty(name = "PCLootAuthorityTime", value = "300000")
    public static int PC_LOOT_AUTHORITY_TIME;
    @ConfigProperty(name = "PopUpToggle", value = "1")
    public static int POP_UP_TOGGLE;
    @ConfigProperty(name = "FirstConnect", value = "")
    public static String FIRST_CONNECT;
    @ConfigProperty(name = "PetUseCount", value = "4")
    public static int PET_USE_COUNT;
    @ConfigProperty(name = "AvailableRegisterDayGuildMark", value = "0")
    public static int AVAILABLE_REGISTER_DAY_GUILD_MARK;
    @ConfigProperty(name = "ValksSealUsableCount", value = "10")
    public static int VALKS_SEAL_USABLE_COUNT;
    @ConfigProperty(name = "AutoFishingTime", value = "180000")
    public static int AUTO_FISHING_TIME;
    @ConfigProperty(name = "DropEventCondition", value = "checkPeriod(2016/3/17-10:59, 2016/3/31-06:59);")
    public static String DROP_EVENT_CONDITION;
    @ConfigProperty(name = "DropEventMainGroupKey", value = "20997")
    public static int DROP_EVENT_MAIN_GROUP_KEY;
    @ConfigProperty(name = "CollectDropEventCondition", value = "checkPeriod(2016/3/25-23:59, 2016/3/26-23:59);")
    public static String COLLECT_DROP_EVENT_CONDITION;
    @ConfigProperty(name = "CollectDropEventMainGroupKey", value = "20994")
    public static int COLLECT_DROP_EVENT_MAIN_GROUP_KEY;
    @ConfigProperty(name = "FishingDropEventCondition", value = "checkPeriod(2016/3/25-23:59, 2016/3/26-23:59);")
    public static String FISHING_DROP_EVENT_CONDITION;
    @ConfigProperty(name = "FishingDropEventMainGroupKey", value = "20995")
    public static int FISHING_DROP_EVENT_MAIN_GROUP_KEY;
    @ConfigProperty(name = "ChatConsumeWp", value = "1")
    public static int CHAT_CONSUME_WP;
    @ConfigProperty(name = "ChatBlockByUserConsumeWp", value = "15")
    public static int CHAT_BLOCK_BY_USER_CONSUME_WP;
    @ConfigProperty(name = "ChatBlockByUserPoint", value = "0")
    public static int CHAT_BLOCK_BY_USER_POINT;
    @ConfigProperty(name = "ChatBlockByUserSecond", value = "0")
    public static int CHAT_BLOCK_BY_USER_SECOND;
    @ConfigProperty(name = "InstallableSiegeTentRangeLimit", value = "15000")
    public static int INSTALLABLE_SIEGE_TENT_RANGE_LIMIT;
    @ConfigProperty(name = "InstallableMinorSiegeTentLimitCount", value = "100")
    public static int INSTALLABLE_MINOR_SIEGE_TENT_LIMIT_COUNT;
    @ConfigProperty(name = "PrisonBuffTendency", value = "0,-100000,-200000,-300000,-1000000")
    public static int[] PRISON_BUFF_TENDENCY;
    @ConfigProperty(name = "PrisonBuff", value = "0,57245,57246,57247,57248")
    public static int[] PRISON_BUFF;
    @ConfigProperty(name = "MurdererStateTime", value = "1800")
    public static int MURDERER_STATE_TIME;
    @ConfigProperty(name = "OpenedDesertPK", value = "0")
    public static int OPENED_DESERT_PK;
    @ConfigProperty(name = "DeathPenaltyType", value = "1")
    public static int DEATH_PENALTY_TYPE;
    @ConfigProperty(name = "PenaltyIndex", value = "0")
    public static int PENALTY_INDEX;
    @ConfigProperty(name = "PenaltyIndexInDesert", value = "2")
    public static int PENALTY_INDEX_IN_DESERT;
    @ConfigProperty(name = "NormalBusyStatePercent", value = "110000")
    public static int NORMAL_BUSY_STATE_PERCENT;
    @ConfigProperty(name = "VeryBusyStatePercent", value = "280000")
    public static int VERY_BUSY_STATE_PERCENT;
    @ConfigProperty(name = "ChannelMoveWaitingTime", value = "900000")
    public static int CHANNEL_MOVE_WAITING_TIME;
    @ConfigProperty(name = "ChannelMovePlayerableUserCount", value = "10")
    public static int CHANNEL_MOVE_PLAYERABLE_USER_COUNT;
    @ConfigProperty(name = "ItemMarketRate", value = "50000")
    public static int ITEM_MARKET_RATE;
    @ConfigProperty(name = "ItemMarketPrice", value = "900000")
    public static int ITEM_MARKET_PRICE;
    @ConfigProperty(name = "SpecialStockTic", value = "600000")
    public static int SPECIAL_STOCK_TIC;
    @ConfigProperty(name = "ItemMarketReservationRate", value = "5000")
    public static int ITEM_MARKET_RESERVATION_RATE;
    @ConfigProperty(name = "ItemMarketReservationCount", value = "1")
    public static int ITEM_MARKET_RESERVATION_COUNT;
    @ConfigProperty(name = "ItemMarketReservationResetTime", value = "43200")
    public static int ITEM_MARKET_RESERVATION_RESET_TIME;
    @ConfigProperty(name = "ItemMarketReservationBonus", value = "300000")
    public static int ITEM_MARKET_RESERVATION_BONUS;
    @ConfigProperty(name = "SpecialStockDownRate", value = "30000")
    public static int SPECIAL_STOCK_DOWN_RATE;
    @ConfigProperty(name = "LikeNameFilter", value = "1")
    public static int LIKE_NAME_FILTER;
    @ConfigProperty(name = "BonusDDType", value = "0")
    public static int BONUS_DD_TYPE;
    @ConfigProperty(name = "TradeSupplyCountShop", value = "1")
    public static int TRADE_SUPPLY_COUNT_SHOP;
    @ConfigProperty(name = "MaxPetSkillRate", value = "500000")
    public static int MAX_PET_SKILL_RATE;
    @ConfigProperty(name = "MinorSiegeStartTime", value = "20")
    public static int MINOR_SIEGE_START_TIME;
    @ConfigProperty(name = "MinorSiegeInstallDayChangeTime", value = "23")
    public static int MINOR_SIEGE_INSTALL_DAY_CHANGE_TIME;
    @ConfigProperty(name = "SiegeTentInstallableGuildMemberCount", value = "10")
    public static int SIEGE_TENT_INSTALLABLE_GUILD_MEMBER_COUNT;
    @ConfigComments(comment = {"Localwar limited level.", "Default: 56"})
    @ConfigProperty(name = "LocalwarLimitedLevel", value = "56")
    public static int LOCALWAR_LIMITED_LEVEL;
    @ConfigComments(comment = {"Localwar limited attack.", "Default: 151"})
    @ConfigProperty(name = "LocalwarLimitedAttack", value = "151")
    public static int LOCALWAR_LIMITED_ATTACK;
    @ConfigComments(comment = {"Localwar limited defense.", "Default: 201"})
    @ConfigProperty(name = "LocalwarLimitedDefense", value = "201")
    public static int LOCALWAR_LIMITED_DEFENSE;
    @ConfigComments(comment = {"Localwar limited AD summary.", "Default: 301"})
    @ConfigProperty(name = "LocalwarLimitedADSummary", value = "301")
    public static int LOCALWAR_LIMITED_AD_SUMMARY;
    @ConfigComments(comment = {"Localwar winner reward.", "Default: 450,5;61,1"})
    @ConfigProperty(name = "LocalWarWinnerReward", value = "450,5;61,1")
    public static String LOCALWAR_WINNER_REWARD;
    @ConfigComments(comment = {"Localwar loser reward.", "Default: 450,1;62,1"})
    @ConfigProperty(name = "LocalWarLoserReward", value = "450,1;62,1")
    public static String LOCALWAR_LOSER_REWARD;
    @ConfigComments(comment = {"Localwar kill buff.", "Default: 1623"})
    @ConfigProperty(name = "LocalwarKillBuff", value = "1623")
    public static int LOCALWAR_KILL_BUFF;
    @ConfigComments(comment = {"Localwar start buff.", "Default: 426"})
    @ConfigProperty(name = "LocalwarStartBuff", value = "426")
    public static int LOCALWAR_START_BUFF;
    @ConfigComments(comment = {"Localwar min DD.", "Default: 200"})
    @ConfigProperty(name = "LocalwarMinDD", value = "200")
    public static int LOCALWAR_MIN_DD;
    @ConfigComments(comment = {"Localwar min PV.", "Default: 180"})
    @ConfigProperty(name = "LocalwarMinPV", value = "180")
    public static int LOCALWAR_MIN_PV;
    @ConfigComments(comment = {"Localwar min HIT.", "Default: 580"})
    @ConfigProperty(name = "LocalwarMinHIT", value = "580")
    public static int LOCALWAR_MIN_HIT;
    @ConfigComments(comment = {"Localwar start min count.", "Default: 2"})
    @ConfigProperty(name = "LocalwarStartMinCount", value = "2")
    public static int LOCALWAR_START_MIN_COUNT;
    @ConfigComments(comment = {"Notify world servant tier.", "Default: 7"})
    @ConfigProperty(name = "NotifyWorldServantTier", value = "7")
    public static int NOTIFY_WORLD_SERVANT_TIER;
    @ConfigComments(comment = {"Vehicle movement experience.", "Default: 32"})
    @ConfigProperty(name = "VehicleMovementExperience", value = "32")
    public static int VEHICLE_MOVEMENT_EXPERIENCE;
    @ConfigComments(comment = {"Waiting time for attendance.", "Default: 1800"})
    @ConfigProperty(name = "WaitingTimeForAttendance", value = "1800")
    public static int WAITING_TIME_FOR_ATTENDANCE;
    @ConfigComments(comment = {"Ocean life level experience.", "Default: 20"})
    @ConfigProperty(name = "OceanLifeLevelExperience", value = "20")
    public static int OCEAN_LIFE_LEVEL_EXPERIENCE;
    @ConfigComments(comment = {"Family point.", "Default: 1"})
    @ConfigProperty(name = "FamilyPoint", value = "1")
    public static int FAMILY_POINT;
    @ConfigComments(comment = {"Cash shop gift.", "Default: 1"})
    @ConfigProperty(name = "CashShopGift", value = "1")
    public static int CASH_SHOP_GIFT;
    @ConfigComments(comment = {"Guest account limited level.", "Default: 99"})
    @ConfigProperty(name = "GuestAccountLimitedLevel", value = "99")
    public static int GUEST_ACCOUNT_LIMITED_LEVEL;
}
