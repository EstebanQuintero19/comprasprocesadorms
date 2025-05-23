package co.ucoshop.comprasprocesadorms.util.gson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MapperJsonObjetoJackson implements MapperJsonObjeto {

    @Override
    public Optional<String> ejecutar(Object objeto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return Optional.ofNullable(objectMapper.writeValueAsString(objeto));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Optional<T> ejecutar(String json, Class<T> claseDestino) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return Optional.ofNullable(objectMapper.readValue(json, claseDestino));
        } catch (Exception e) {
            System.err.println("Error al deserializar JSON a " + claseDestino.getSimpleName());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> ejecutarGson(Object objecto) {
        try {
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        @Override
                        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                            return new JsonPrimitive(src.format(formatter));
                        }
                    })
                    .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                        @Override
                        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return LocalDate.parse(json.getAsString().replace("T", ""), DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                    })

                    .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        @Override
                        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                    })
                    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                        @Override
                        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                        }
                    })
                    .create();

            String objeto = gson.toJson(objecto);
            return Optional.ofNullable(objeto);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}