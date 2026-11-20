package mx.neodevsoft.process.notifications.batchconfig;

import java.util.Arrays;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class FirstStepReader implements ItemReader<Integer> {

	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

	int i = 0;
	
	@Override
	public @Nullable Integer read() throws Exception {
		
		System.out.println("Inside Item reader");
		
		Integer item;
		if(i < list.size()) {
			item = list.get(i);
			i++;
			return item;
			
		}
		i = 0;
		return null;
	}
	
	
	
}
