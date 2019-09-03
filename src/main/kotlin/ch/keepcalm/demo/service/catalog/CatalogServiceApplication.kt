package ch.keepcalm.demo.service.catalog

import io.jaegertracing.Configuration
import io.jaegertracing.internal.samplers.ConstSampler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors

@SpringBootApplication
class CatalogServiceApplication

fun main(args: Array<String>) {
    runApplication<CatalogServiceApplication>(*args)
}

@Component
class TracerConfiguration {

    @Bean
    fun jaegerTracer() = Configuration("catalog-service")
            .withSampler(Configuration.SamplerConfiguration
                    .fromEnv()
                    .withType(ConstSampler.TYPE)
                    .withParam(1))
            .withReporter(Configuration.ReporterConfiguration
                    .fromEnv()
                    .withLogSpans(true))
}


@RestController
@RequestMapping("/api/v1/animals")
class AnimalNameResource(/*private val tracer: Tracer?*/ private val animalNameService: AnimalNameService) {


    @GetMapping(path = ["/random"])
    fun getAnimalNames(@RequestHeader headers: HttpHeaders): String {
        return animalNameService.getRandomNames()
    }

}

@Service
class AnimalNameService() {

    private var animalNames: List<String> = listOf()
    private val random: Random

    init {
        val inputStream = ClassPathResource("/animals.txt").inputStream
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            animalNames = reader.lines().collect(Collectors.toList<String>())
        }
        random = Random()
    }

    fun getRandomNames() = animalNames[random.nextInt(animalNames.size)]

}
