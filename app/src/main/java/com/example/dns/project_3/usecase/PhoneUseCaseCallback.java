package com.example.dns.project_3.usecase;

public class PhoneUseCaseCallback {

    /*private RequestLenta lenta = LentaNetwork.getInstance();*/

    public void execute(UseCaseCallback<String> collback, String phone)
    {

        /*Phone telephone = new Phone(phone);
        Call<String> client = lenta.postPnone(telephone);

        client.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                collback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                collback.onError("Ошибка подключения, повторите попытку");
            }
        });*/

        collback.onSuccess("");
    }
}
