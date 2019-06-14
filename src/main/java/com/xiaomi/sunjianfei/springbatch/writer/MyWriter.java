package com.xiaomi.sunjianfei.springbatch.writer;
import java.util.List;

import com.xiaomi.sunjianfei.springbatch.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class MyWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		for(User user : items){
			log.info("user={}",user);
		}
	}

}
