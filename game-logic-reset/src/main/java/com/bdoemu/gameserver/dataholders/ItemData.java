package com.bdoemu.gameserver.dataholders;

import com.bdoemu.commons.reload.IReloadable;
import com.bdoemu.commons.reload.Reloadable;
import com.bdoemu.commons.utils.ServerInfoUtils;
import com.bdoemu.core.startup.StartupComponent;
import com.bdoemu.gameserver.model.items.templates.ItemEnchantT;
import com.bdoemu.gameserver.model.items.templates.ItemTemplate;
import com.bdoemu.gameserver.service.database.SQLiteDatabaseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName ItemData
 * @Description  道具数据
 * @Author JiangBangMing
 * @Date 2019/7/8 21:43
 * VERSION 1.0
 */

@Reloadable(name = "item", group = "sqlite")
@StartupComponent("Data")
public class ItemData implements IReloadable {

    private static final Logger log;
    private static final AtomicReference<Object> instance;

    static {
        log = LoggerFactory.getLogger((Class) ItemData.class);
        instance = new AtomicReference<>();
    }

    private HashMap<Integer, ItemTemplate> templates;

    public ItemData() {
        this.templates = new HashMap<>();
        this.load();
    }

    public static ItemData getInstance() {
        Object value = ItemData.instance.get();
        if (value == null) {
            synchronized (ItemData.instance) {
                value = ItemData.instance.get();
                if (value == null) {
                    final ItemData actualValue = new ItemData();
                    value = ((actualValue == null) ? ItemData.instance : actualValue);
                    ItemData.instance.set(value);
                }
            }
        }
        return (ItemData) ((value == ItemData.instance) ? null : value);
    }

    public Collection<ItemTemplate> getTemplates() {
        return this.templates.values();
    }

    public ItemTemplate getItemTemplate(final int templateId) {
        ItemTemplate tpl = this.templates.get(templateId);
        if (tpl == null) {
            log.error("Template does not exist for {} item id!!!!", templateId);
            return null;
        }
        return tpl;
    }

    private void load() {
        ServerInfoUtils.printSection("ItemData Loading");
        final HashMap<Integer, HashMap<Integer, ItemEnchantT>> enchantTemplates = new HashMap<>();
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM '1_LongSword'")) {
            int loadedTemplates = 0;
            while (rs.next()) {
                final int templateId = rs.getInt("Index");
                final ItemEnchantT template = new ItemEnchantT(rs);
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template.getContentsGroupKey())) {
                    if (!enchantTemplates.containsKey(templateId)) {
                        enchantTemplates.put(templateId, new HashMap<>());
                    }
                    enchantTemplates.get(templateId).put(template.getEnchantLevel(), template);
                    ++loadedTemplates;
                }
            }
            ItemData.log.info("Loaded {} ItemEnchantTemplate's from 1_LongSword table",  loadedTemplates);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load LongSword ItemEnchantT",  ex);
        }
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM '2_Blunt'")) {
            int loadedTemplates = 0;
            while (rs.next()) {
                final int templateId = rs.getInt("Index");
                final ItemEnchantT template = new ItemEnchantT(rs);
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template.getContentsGroupKey())) {
                    if (!enchantTemplates.containsKey(templateId)) {
                        enchantTemplates.put(templateId, new HashMap<>());
                    }
                    enchantTemplates.get(templateId).put(template.getEnchantLevel(), template);
                    ++loadedTemplates;
                }
            }
            ItemData.log.info("Loaded {} ItemEnchantTemplate's from 2_Blunt table",  loadedTemplates);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load Blunt ItemEnchantT",  ex);
        }
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM '3_TwoHandSword'")) {
            int loadedTemplates = 0;
            while (rs.next()) {
                final int templateId = rs.getInt("Index");
                final ItemEnchantT template = new ItemEnchantT(rs);
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template.getContentsGroupKey())) {
                    if (!enchantTemplates.containsKey(templateId)) {
                        enchantTemplates.put(templateId, new HashMap<>());
                    }
                    enchantTemplates.get(templateId).put(template.getEnchantLevel(), template);
                    ++loadedTemplates;
                }
            }
            ItemData.log.info("Loaded {} ItemEnchantTemplate's from 3_TwoHandSword table",  loadedTemplates);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load TwoHandSword ItemEnchantT",  ex);
        }
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM '4_Bow'")) {
            int loadedTemplates = 0;
            while (rs.next()) {
                final int templateId = rs.getInt("Index");
                final ItemEnchantT template = new ItemEnchantT(rs);
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template.getContentsGroupKey())) {
                    if (!enchantTemplates.containsKey(templateId)) {
                        enchantTemplates.put(templateId, new HashMap<>());
                    }
                    enchantTemplates.get(templateId).put(template.getEnchantLevel(), template);
                    ++loadedTemplates;
                }
            }
            ItemData.log.info("Loaded {} ItemEnchantTemplate's from 4_Bow table",  loadedTemplates);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load Bow ItemEnchantT", ex);
        }
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM '5_Dagger'")) {
            int loadedTemplates = 0;
            while (rs.next()) {
                final int templateId = rs.getInt("Index");
                final ItemEnchantT template = new ItemEnchantT(rs);
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template.getContentsGroupKey())) {
                    if (!enchantTemplates.containsKey(templateId)) {
                        enchantTemplates.put(templateId, new HashMap<>());
                    }
                    enchantTemplates.get(templateId).put(template.getEnchantLevel(), template);
                    ++loadedTemplates;
                }
            }
            ItemData.log.info("Loaded {} ItemEnchantTemplate's from 5_Dagger table",  loadedTemplates);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load Dagger ItemEnchantT",  ex);
        }
        try (final Connection con = SQLiteDatabaseFactory.getInstance().getCon();
             final Statement statement = con.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM 'Item_Table'")) {
            int disabledByContentOptions = 0;
            final ResultSetMetaData rsMeta = rs.getMetaData();
            while (rs.next()) {
                final int templateId2 = rs.getInt("Index");
                final ItemTemplate template2 = new ItemTemplate(rs, rsMeta, templateId2, enchantTemplates.get(templateId2));
                if (ContentsGroupOptionData.getInstance().isContentsGroupOpen(template2.getContentsGroupKey())) {
                    this.templates.put(templateId2, template2);
                } else {
                    ++disabledByContentOptions;
                }
            }
            ItemData.log.info("Loaded {} ItemTemplate's. ({} disabled by ContentsGroupOptionData)",  this.templates.size(), disabledByContentOptions);
        } catch (SQLException ex) {
            ItemData.log.error("Failed load ItemTemplate", ex);
        }
    }
    @Override
    public void reload() {
        this.load();
    }
}
