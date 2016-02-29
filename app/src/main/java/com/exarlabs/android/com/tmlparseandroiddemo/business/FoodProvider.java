package com.exarlabs.android.com.tmlparseandroiddemo.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It just mocks a list of foods
 * Created by becze on 2/26/2016.
 */
public class FoodProvider {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    public enum Food {
        STEAK_WITH_LEMON("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/iron_rich_foods_ref_guide/375x321_iron_rich_foods_ref_guide.jpg", "Steak With Lemon", "There are two forms of dietary iron: heme and nonheme. Heme " +
                        "iron is derived from hemoglobin. It is found in animal foods that originally contained hemoglobin, such as red meats, fish, and poultry. Your body absorbs the most iron from heme sources. Nonheme iron is from plant sources."),
        LEMON_DILL_CHICKEN("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/ew_lemon_dill_chicken_recipe/300x310_ew_lemon_dill_chicken_recipe.jpg", "Lemon Dill Chicken", "Fresh lemon and dill create a quick Greek-inspired pan " +
                        "sauce for simple sautéed chicken breasts. Make it a meal: Serve with roasted broccoli and whole-wheat orzo."),
        PLANK_GRILLED_SALMON("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/ew_plank_grilled_salmon_with_creamy_tarragon_sauce_recipe/300x310_ew_plank_grilled_salmon_with_creamy_tarragon_sauce_recipe.jpg", "Plank Grilled Salmon",
                             "Using a plank to grill fish keeps it from sticking or falling through the grate and imparts a subtle smoky flavor to the salmon. "),

        FRUIT_SALAD("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/eating_well/2015/300x310_ew_banana_kiwi_salad_5082_recipe.jpg", "50/50 Fruit Salad (Or Fruit Dip)",
                    "Remember those 50/50 orange-and-cream bars? They were the inspiration for this recipe."),
        GINGER_BROCCOLI("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/eating_well/2015/300x310_ew_ginger_broccoli_6202_recipe.jpg", "Ginger Broccoli",
                        "Broccoli gets a Southeast Asian treatment in this quick sauté with fresh ginger, mellow rice vinegar and rich, salty fish sauce. Serve alongside any Asian noodle or fried rice dish."),

        FLOURLESS_CAKE("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/ew_flourless_honey_almond_cake_recipe/300x310_ew_flourless_honey_almond_cake_recipe.jpg", "Flourless Honey-Almond Cake", "Honey and almonds flavor this simple (and " +
                        "gluten-free) cake. It’s lovely for afternoon tea or a spring holiday dessert. Be careful not to overbeat the egg whites—they should be white and very foamy, but not at all stiff or able to hold peaks."),
        SOUP("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/ew_golden_summer_squash_and_corn_soup_recipe/300x310_ew_golden_summer_squash_and_corn_soup_recipe.jpg", "Golden Summer Squash and Corn Soup",
             "Pureed summer squash makes a delicious base for this summery squash and corn soup. Start your meal with the soup or enjoy it as a light lunch."),
        LINGUINE("http://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/recipes/ew_linguine_with_escarole_and_shrimp_recipe/300x310_ew_linguine_with_escarole_and_shrimp_recipe.jpg", "Linguine With Escarole and Shrimp",
                 "Lots of tangy lemon, fresh tomatoes, escarole and shrimp create an " +
                                 "incredible sauce for whole-wheat pasta. Serve with a glass of Sauvignon Blanc and whole-grain bread.");

        Food(String imgUrl, String title, String smallDescription) {
            mImageURL = imgUrl;
            mTitle = title;
            mSmallDescription = smallDescription;
        }

        public String mImageURL;
        public String mTitle;
        public String mSmallDescription;
    }


    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    /**
     * @return a listof Food objects
     */
    public List<Food> getFoodList() {
        List<Food> foods = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            foods.addAll(Arrays.asList(Food.values()));
        }

        return foods;
    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
