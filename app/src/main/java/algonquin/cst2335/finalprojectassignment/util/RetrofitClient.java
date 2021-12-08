package algonquin.cst2335.finalprojectassignment.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private RetrofitApi myRetrofitApi;


    private RetrofitClient() {

//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder().addHeader("Authorization", "563492ad6f91700001000001ccf874f4226c45ed81afe47644d05f27").build();
//                return chain.proceed(request);
//            }
//        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build())
                .build();
        myRetrofitApi = retrofit.create(RetrofitApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public RetrofitApi getMyRetrofitApi() {
        return myRetrofitApi;
    }


}
