package mx.neodevsoft.process.notifications.batchconfig;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchClass {

	
	@Bean
	public Job helloWorldJob(JobRepository jobRepository, Step helloWorldStep) {
		return new JobBuilder("helloWorldJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(helloWorldStep)
				.build();
	}

	@Bean
	public Step helloWorldStep(JobRepository jobRepository) {
		return new StepBuilder("helloWorldStep", jobRepository).tasklet(firstTask())
				.build();
	}

	private Tasklet firstTask() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				System.out.println("This is first tasklet step");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	
	
}