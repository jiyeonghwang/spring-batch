package example.io.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Repeatable;

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob(){
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }
    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet(){
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{

                        System.out.println(" ====================== ");
                        System.out.println(" >> Hello Spring Batch ");
                        System.out.println(" ====================== ");

                        // return  null; 한번 실행 후 종료
                        return RepeatStatus.FINISHED; // null과 동일, 한번실행 후 종료
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet(){
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{

                        System.out.println(" ====================== ");
                        System.out.println(" >> Hello Step Two !! ");
                        System.out.println(" ====================== ");

                        // return  null; 한번 실행 후 종료
                        return RepeatStatus.FINISHED; // null과 동일, 한번실행 후 종료
                    }
                })
                .build();
    }
}
