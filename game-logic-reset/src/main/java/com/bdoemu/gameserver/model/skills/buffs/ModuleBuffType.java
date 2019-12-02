package com.bdoemu.gameserver.model.skills.buffs;

import com.bdoemu.gameserver.model.skills.buffs.effects.ActionResistanceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ActiveBuffDispellingEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ActiveRegenSubResourcePointVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AddActionRestrictEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AddCharacterSlotEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AddGuardTypeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AddedEtcItemPercentEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AdditionalDamageVariationEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AggroVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AquireQuestEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AquireRateHighKnowledgeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AquireRateKnowledgeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AquireSkillPointEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AttackDamageVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.AttackSpeedVariationRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.BlockDriverSeatEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CastingSpeedVariationRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CatchFishChanceAddEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ChangeBlackSpiritMonsterEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CollectorEquipmentEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CreateCardEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CriticalVariationRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CurrentHpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CurrentMpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.CurrentSubResourcePointVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.DecreaseTimeForProductResultItemEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.DefenceByScrollEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.DefenceVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.DropRateCollectEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.EfficiencyOfTamingAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ElephantTrapEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.EnchantFailCountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.EscortNpcEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.EvasionVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ExpandInventoryEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ExpandWarehouseEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ExperienceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ExperienceRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.FearCharacterEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.FireworksEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.FitnessSupplementEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.FixedDamageEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.HPAddPercentageEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.HideActionEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.HideIdentityEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.HitDamageVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.LearnSkillEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.LifeExperienceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.MaxHpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.MaxMpVariationAmount;
import com.bdoemu.gameserver.model.skills.buffs.effects.MaxSpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.MaxSubResourcePointVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.MoveSpeedVariationRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.NotInteractionEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.NpcWorkerActionPointEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.PremiumChargeUserEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RecoveryBreathGaugeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ReduceAutoFishingTimePercent;
import com.bdoemu.gameserver.model.skills.buffs.effects.RegenHpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RegenMpVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RegenSubResourcePointVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RegisterExplorationNodeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RegisterWorkerEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ResistanceEnduranceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RevivalEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RevivalOthersEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.RideOffEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.ShieldGuardVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.SkipDeathPenaltyEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.SummonCharacterEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.SwimmingSpeedEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.TamingEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.TendencyAquireRateEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.TentHavestGrowCorrectionEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.TransformationEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.TribeDamageVariationAmountEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryAddExpRateHorseEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryAddSkillExpRateHorseEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryBreathGaugeEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryDamageToMonstersEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryDistanceOfJumpEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryDistanceOfViewEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryDropDamageResistanceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryEfficiencyOfIntimacyEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryExplorePointEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryHeatstrokeResistanceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryMaxPointStatEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryPermanentWeightEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryPointStatEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryRegenWpEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryServantStatEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VarySkillPieceEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryTendency;
import com.bdoemu.gameserver.model.skills.buffs.effects.VaryWpEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.VersusMonsterStatEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.WarpEffect;
import com.bdoemu.gameserver.model.skills.buffs.effects.WeightVariationAmountEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ModuleBuffType
 * @Description  模块buff 类型
 * @Author JiangBangMing
 * @Date 2019/7/6 12:04
 * VERSION 1.0
 */
public enum ModuleBuffType {
    GUILD_UNK(0, null),
    CURRENT_HP_VARIATION_AMOUNT(1, new CurrentHpVariationAmountEffect()),
    MAX_HP_VARIATION_AMOUNT(2, new MaxHpVariationAmountEffect()),
    REGEN_HP_VARIATION_AMOUNT(3, new RegenHpVariationAmountEffect()),
    CURRENT_MP_VARIATION_AMOUNT(4, new CurrentMpVariationAmountEffect()),
    MAX_MP_VARIATION_AMOUNT(5, new MaxMpVariationAmount()),
    REGEN_MP_VARIATION_AMOUNT(6, new RegenMpVariationAmountEffect()),
    INFINITE_SP(7, null),
    MAX_SP_VARIATION_AMOUNT(8, new MaxSpVariationAmountEffect()),
    MOVE_SPEED_VARIATION_RATE(9, new MoveSpeedVariationRateEffect()),
    ATTACK_SPEED_VARIATION_RATE(10, new AttackSpeedVariationRateEffect()),
    CASTING_SPEED_VARIATION_RATE(11, new CastingSpeedVariationRateEffect()),
    BASIC_STAT_VARIATION_AMOUNT(12, null),
    HUNGER_STATE_VARIATION_AMOUNT(13, null),
    ADD_ACTION_RESTRICT(14, new AddActionRestrictEffect()),
    ACTION_RESTRICTION_RELEASE(15, null),
    ACTIVE_BUFF_DISPELLING(16, new ActiveBuffDispellingEffect()),
    LEARN_SKILL(17, new LearnSkillEffect()),
    SUMMON_CHARACTER(18, new SummonCharacterEffect()),
    FEAR_CHARACTER(20, new FearCharacterEffect()),
    WARP(23, new WarpEffect()),
    EXPERIENCE(24, new ExperienceEffect()),
    EXPERIENCE_RATE(25, new ExperienceRateEffect()),
    SIEGE_WEAPON_DAMAGE(28, null),
    WEIGHT_VARIATION_AMOUNT(29, new WeightVariationAmountEffect()),
    CRITICAL_VARIATION_RATE(30, new CriticalVariationRateEffect()),
    AGGRO_VARIATION_AMOUNT(31, new AggroVariationAmountEffect()),
    REVIVAL(32, new RevivalEffect()),
    HIDE_IDENTITY(33, new HideIdentityEffect()),
    TRANSFORMATION(34, new TransformationEffect()),
    NOT_INTERACTION(35, new NotInteractionEffect()),
    ACTION_RESTRICTION(36, null),
    REGISTER_EXPLORATION_NODE(37, new RegisterExplorationNodeEffect()),
    CREATE_CARD(38, new CreateCardEffect()),
    ATTACK_DAMAGE_VARIATION_AMOUNT(39, new AttackDamageVariationAmountEffect()),
    HIT_DAMAGE_VARIATION_AMOUNT(40, new HitDamageVariationAmountEffect()),
    EVASION_VARIATION_AMOUNT(41, new EvasionVariationAmountEffect()),
    DEFENCE_VARIATION_AMOUNT(43, new DefenceVariationAmountEffect()),
    DEFENCE_BY_SCROLL_EFFECT(44, new DefenceByScrollEffect()),
    FIXED_DAMAGE(45, new FixedDamageEffect()),
    TRIBE_DAMAGE_VARIATION_AMOUNT(46, new TribeDamageVariationAmountEffect()),
    EFFICIENCY_OF_TAMING_AMOUNT(47, new EfficiencyOfTamingAmountEffect()),
    UNK_48(48, new VarySkillPieceEffect()),
    ACTION_RESISTANCE(49, new ActionResistanceEffect()),
    VARY_ADD_EXP_RATE_HORSE(50, new VaryAddExpRateHorseEffect()),
    VARY_ADD_SKILL_EXP_RATE_HORSE(51, new VaryAddSkillExpRateHorseEffect()),
    VARY_DROP_DAMAGE_RESISTANCE(52, new VaryDropDamageResistanceEffect()),
    VARY_DISTANCE_OF_VIEW(53, new VaryDistanceOfViewEffect()),
    VARY_EFFICIENCY_OF_TRADE_IN_SHOP(54, null),
    VARY_REDUCE_VALUE_OF_QUEST_DEMAND(55, null),
    VARY_EFFICIENCY_OF_INTIMACY(56, new VaryEfficiencyOfIntimacyEffect()),
    VARY_EFFICIENCY_OF_DROP_ITEM(57, null),
    VARY_VISION_RANGE(58, null),
    VARY_DISTANCE_OF_JUMP(59, new VaryDistanceOfJumpEffect()),
    VARY_EXPLORE_POINT(60, new VaryExplorePointEffect()),
    ENTER_DUNGEON(61, null),
    AQUIRE_SKILL_POINT(62, new AquireSkillPointEffect()),
    NPC_WORKER_ACTION_POINT(63, new NpcWorkerActionPointEffect()),
    HIDE_ACTION(64, new HideActionEffect()),
    VARY_PROTECT_RATE_OF_TRADE_ITEM(65, null),
    VARY_REGEN_WP(66, new VaryRegenWpEffect()),
    VARY_POINT_STAT(67, new VaryPointStatEffect()),
    VARY_MAX_POINT_STAT(68, new VaryMaxPointStatEffect()),
    AQUIRE_QUEST(69, new AquireQuestEffect()),
    HPADD_PERCENTAGE_EFFECT(70, new HPAddPercentageEffect()),
    EXPAND_INVENTORY_EFFECT(71, new ExpandInventoryEffect()),
    EXPAND_WAREHOUSE_EFFECT(72, new ExpandWarehouseEffect()),
    TRADE_STOCK_REFRESH(73, null),
    VARY_SUPER_SKILL_RATE(74, null),
    STOP_REGEN(75, null),
    VARY_TENDENCY(76, new VaryTendency()),
    VARY_MORALE(77, null),
    COLLECTOR_EQUIPMENT(78, new CollectorEquipmentEffect()),
    VARY_WP(79, new VaryWpEffect()),
    LIFE_EXPERIENCE(80, new LifeExperienceEffect()),
    EFFICIENCY_OF_FISHING(81, null),
    FLASH_BANG(82, null),
    TENT_HAVEST_GROW_CORRECTION(83, new TentHavestGrowCorrectionEffect()),
    FIREWORKS_EFFECT(84, new FireworksEffect()),
    CURRENT_SUB_RESOURCE_POINT_VARIATION_AMOUNT(85, new CurrentSubResourcePointVariationAmountEffect()),
    MAX_SUB_RESOURCE_POINT_VARIATION_AMOUNT(86, new MaxSubResourcePointVariationAmountEffect()),
    REGEN_SUB_RESOURCE_POINT_VARIATION_AMOUNT(87, new RegenSubResourcePointVariationAmountEffect()),
    ACTIVE_REGEN_SUB_RESOURCE_POINT_VARIATION_AMOUNT(88, new ActiveRegenSubResourcePointVariationAmountEffect()),
    FITNESS_SUPPLEMENT(89, new FitnessSupplementEffect()),
    SKIP_DEATH_PENALTY(90, new SkipDeathPenaltyEffect()),
    RESISTANCE_ENDURANCE(91, new ResistanceEnduranceEffect()),
    RESISTANCE_SIEGE_WEAPON(92, null),
    ADDITIONAL_DAMAGE_VARIATION(93, new AdditionalDamageVariationEffect()),
    ADDED_MAX_WP(94, null),
    VARY_BREATH_GAUGE(95, new VaryBreathGaugeEffect()),
    SPECIAL_ATTACK_EVASION(96, null),
    PREMIUM_CHARGE_USER(97, new PremiumChargeUserEffect()),
    VARY_SERVANT_STAT(98, new VaryServantStatEffect()),
    VARY_PERMANENT_WEIGHT(99, new VaryPermanentWeightEffect()),
    ADD_CHARACTER_SLOT(100, new AddCharacterSlotEffect()),
    CREATE_RANDOM_CARD(101, null),
    TAMING_EFFECT(102, new TamingEffect()),
    REGISTER_WORKER(103, new RegisterWorkerEffect()),
    TENDENCY_AQUIRE_RATE(104, new TendencyAquireRateEffect()),
    ADD_GUARD_TYPE(105, new AddGuardTypeEffect()),
    DAMAGE_RESISTANCE(106, null),
    DROP_RATE_COLLECT(107, new DropRateCollectEffect()),
    AQUIRE_RATE_KNOWLEDGE(108, new AquireRateKnowledgeEffect()),
    AQUIRE_RATE_HIGH_KNOWLEDGE(109, new AquireRateHighKnowledgeEffect()),
    CHANGE_BLACK_SPIRIT_MONSTER(110, new ChangeBlackSpiritMonsterEffect()),
    DECREASE_TIME_FOR_PRODUCT_RESULT_ITEM(111, new DecreaseTimeForProductResultItemEffect()),
    ADDED_ETC_ITEM_PERCENT(112, new AddedEtcItemPercentEffect()),
    STORAGE_PROCESSING(114, null),
    ENCHANT_FAIL_COUNT_EFFECT(115, new EnchantFailCountEffect()),
    SHIELD_GUARD_VARIATION_AMOUNT(116, new ShieldGuardVariationAmountEffect()),
    MANA_ABSORB_DAMAGE_REDUCTION(117, null),
    REVIVAL_OTHERS(119, new RevivalOthersEffect()),
    VERSUS_MONSTER_STAT(120, new VersusMonsterStatEffect()),
    REDUCE_AUTO_FISHING_TIME_PERCENT(121, new ReduceAutoFishingTimePercent()),
    CALL_HANDLER_TO_AI(122, null),
    CUSTOMIZATION_FREE(123, null),
    ELEPHANT_TRAP_EFFECT(124, new ElephantTrapEffect()),
    DUTY_FREE(125, null),
    CATCH_FISH_CHANCE_ADD_EFFECT(126, new CatchFishChanceAddEffect()),
    RECALL_GUILD_MEMBERS(127, null),
    VARY_HEATSTROKE_RESISTANCE(128, new VaryHeatstrokeResistanceEffect()),
    VARY_MAX_BLACK_SPIRIT_RAGE(129, null),
    MARKET_BID_CHANCE_VARIATION(130, null),
    VARY_DESERT_TRADE_PRICE(131, null),
    RIDE_OFF(132, new RideOffEffect()),
    USE_COMPASS(133, null),
    SWIMMING_SPEED_EFFECT(134, new SwimmingSpeedEffect()),
    VARY_DAMAGE_TO_MONSTERS(136, new VaryDamageToMonstersEffect()),
    RECOVERY_BREATH_GAUGE(137, new RecoveryBreathGaugeEffect()),
    BLOCK_DRIVER_SEAT(138, new BlockDriverSeatEffect()),
    UNKNOWN(139, null),
    UNKNOWN2(140, null),
    PET_LEVEL_FEED(141, null),
    TAKE_ME(142, null),
    ESCORT_NPC(349, new EscortNpcEffect());
    private static final Logger log = LoggerFactory.getLogger(ModuleBuffType.class);
    private int id;
    private ABuffEffect effect;

    ModuleBuffType(final int id, final ABuffEffect effect) {
        this.id = id;
        this.effect = effect;
    }

    public static ModuleBuffType valueOf(final int id) {
        for (final ModuleBuffType moduleBuffType : values()) {
            if (moduleBuffType.id == id) {
                return moduleBuffType;
            }
        }
        ModuleBuffType.log.warn("Found unimplemented EModuleBuffType: {}", id);
        return null;
    }

    public ABuffEffect getEffect() {
        return this.effect;
    }
}
