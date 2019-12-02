package com.bdoemu.login.databaseCollections;

import com.bdoemu.commons.database.mongo.ADatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseCollection;
import com.bdoemu.commons.database.mongo.DatabaseLockInfo;
import com.bdoemu.login.idfactory.LSIDStorageType;
import com.bdoemu.login.idfactory.LoginServerIDFactory;
import com.bdoemu.login.models.db.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName PaymentsDbCollection
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/6/26 19:43
 * VERSION 1.0
 */

@DatabaseCollection
public class PaymentsDbCollection extends ADatabaseCollection<Payment, LoginServerIDFactory> {
    private static final Logger log= LoggerFactory.getLogger(PaymentsDbCollection.class);

    private PaymentsDbCollection(final Class<Payment> clazz) {
        super(clazz, "payments");
        this.addLockInfo(new DatabaseLockInfo(LSIDStorageType.PAYMENT, ""));
    }

    public static PaymentsDbCollection getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        static final PaymentsDbCollection INSTANCE = new PaymentsDbCollection(Payment.class);
    }

}
