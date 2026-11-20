package mx.neodevsoft.process.notifications.batchconfig;

import javax.sql.DataSource;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import mx.neodevsoft.process.notifications.domain.entity.StudentCsv;

@Configuration
public class BatchClass {


	@Autowired
	private FirstStepReader firstItemReader;
	@Autowired
	private FirstItemProcesor firstItemProcessor;
	@Autowired
	private FirstItemWriter firstItemWritter;

	@Autowired 
	DataSource dataSource;
	
	@Bean
	public Job helloWorldJob(JobRepository jobRepository, Step helloWorldStep) {
		return new JobBuilder("helloWorldJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep(jobRepository))
				.build();
	}

	
	
	@Bean
	    public Step firstChunkStep(JobRepository jobRepository) {
	        return new StepBuilder("first chunk Step", jobRepository)
	          .<StudentCsv, StudentCsv> chunk(3)
	          .reader(csvItemReader())
	          .processor(firstItemProcessor)
	          .writer(writer(dataSource))
	          .build();
	    }
	

		public FlatFileItemReader<StudentCsv> csvItemReader() {
			return new FlatFileItemReaderBuilder<StudentCsv>()
					.name("csvItemReader")
					.resource(new ClassPathResource("inputFiles/students.csv"))
					.delimited()
					.names("id", "firstName", "lastName", "email")
					.targetType(StudentCsv.class)
					.linesToSkip(1)
					.build();
		}

    public JdbcBatchItemWriter<StudentCsv> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<StudentCsv>()
          .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
          .sql("INSERT INTO student (id, firstName, lastName, email) VALUES (:id, :firstName, :lastName, :email)")
          .dataSource(dataSource)
          .build();
    }

}