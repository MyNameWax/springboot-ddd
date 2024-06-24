package cn.rzpt.domain.support.ids.policy;

import cn.rzpt.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;


@Component
public class ShortCode implements IIdGenerator {
    @Override
    public synchronized long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        StringBuffer idStr = new StringBuffer();
        idStr.append(year-2022);
        idStr.append(hour);
        idStr.append(String.format("%02d",week));
        idStr.append(day);
        idStr.append(String.format("%03d",new Random().nextInt(1000)));
        return Long.parseLong(idStr.toString());
    }
}