package com.xiaomi.sunjianfei.springbatch.processor;
import com.xiaomi.sunjianfei.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;

public class MyProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		if (Integer.parseInt(item.getAge()) % 2 == 0) {
			return item;
		}
		return null;
	}

}
