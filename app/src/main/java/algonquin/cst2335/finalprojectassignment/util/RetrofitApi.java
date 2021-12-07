package algonquin.cst2335.finalprojectassignment.util;

import algonquin.cst2335.finalprojectassignment.model.PexelsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitApi {

    String BASE_URL = "https://api.pexels.com/v1/";

    @Headers({"Authorization: 563492ad6f91700001000001ccf874f4226c45ed81afe47644d05f27"})
    @GET("search")
    Call<PexelsResponse> getData(@Query("query") String query);
}
