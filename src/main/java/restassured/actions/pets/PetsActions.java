package restassured.actions.pets;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import restassured.Pet.classes.MessageResponse;
import restassured.Pet.classes.Pet;
import restassured.Pet.classes.StatusEnum;
import restassured.tests.Authentication;

import java.util.List;

import static io.restassured.RestAssured.given;
import static restassured.tests.Constants.*;

/**
 * Created by Ahmed Ali 07/17/2020
 */
public class PetsActions {
    public static String PET_ENDPOINT = BASE_URL + "/pet";
    private RequestSpecification requestSpecification;

    public PetsActions() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader("api_key", Authentication.Login("britks", "password"))
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
    }

    public Pet addNewPet(Pet request) {
        return given(requestSpecification)
                .body(request)
                .post(PET_ENDPOINT).as(Pet.class);
    }

    public List<Pet> getPetsByStatus(StatusEnum status) {
        return given(requestSpecification)
                .queryParam("status", StatusEnum.available.toString())
                .get(PET_ENDPOINT + "/findByStatus")
                .then().log().all()
                .extract().body()
                .jsonPath().getList("", Pet.class);

    }

    public void deletePet(String petId) {
        given(requestSpecification)
                .pathParam("petId", petId)
                .delete(PET_ENDPOINT + "/{petId}");
    }

    public void deletePet(Pet pet) {
        given(requestSpecification)
                .pathParam("petId", pet.getId())
                .delete(PET_ENDPOINT + "/{petId}");
    }


    public boolean isPetExists(Pet pet) {
        return isPetExists(pet.getId());
    }

    public boolean isPetExists(String petId) {
        return !given(requestSpecification)
                .pathParam("petId", petId)
                .get(PET_ENDPOINT + "/{petId}")
                .then()
                .extract().body().jsonPath().getObject("", MessageResponse.class)
                .getMessage().equals("Pet not found");
    }

}
