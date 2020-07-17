package restassured.pet.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import restassured.Pet.classes.Pet;
import restassured.Pet.classes.StatusEnum;
import restassured.actions.pets.PetsActions;
import restassured.util.BaseClass;

import java.util.List;

/**
 * Created by Ahmed Ali 07/17/2020.
 */
public class PetTestsUsingLittleLibrary extends BaseClass {
    PetsActions petsActions;

    @BeforeClass
    public void beforeClass() {
        petsActions = new PetsActions();
    }

    @Test
    public void getPetsByStatus() {
        List<Pet> pets = petsActions.getPetsByStatus(StatusEnum.available);
        for (Pet pet : pets) {
            Assert.assertEquals(pet.getStatus(), StatusEnum.available);
        }
    }

    @Test
    public void addNewPet() {
        Pet petRequest = new Pet();
        Pet petResponse = petsActions.addNewPet(petRequest);
        Assert.assertEquals(petRequest, petResponse);
    }


    @Test
    public void deletePetItem() {
        Pet petRequest = new Pet();
        Pet response = petsActions.addNewPet(petRequest);
        petsActions.deletePet(response);
        Assert.assertTrue(!petsActions.isPetExists(response));
    }
}
