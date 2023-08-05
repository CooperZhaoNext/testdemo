package org.example;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;

public class CheckUUIDMain {


    public static void main(String[] args) {

        List<String> sjk = Lists.newArrayList(
            "7a8f6192-5d53-4130-8a3a-f5fb96384ccd",
            "0b908377-d6ea-4efd-85df-13d9415af237",
            "1e55609a-b921-4e1f-a47e-d660b4bef404",
            "a9c78d9c-2334-43f3-a55c-53d430d12697",
            "937a6307-e030-48d7-aae3-fbf0a5ffd76b",
            "3a9dcf11-07d7-4c42-8112-3b35c2c329e6",
            "ec3989ae-bd7b-43ea-8ada-a5f947793cf9",
            "8a641789-06c7-4151-b0dc-a2c023a1fa90",
            "6bd56b34-1287-4188-83e0-bf4a52f31772",
            "c0bbca2b-52f0-4801-a0b8-38dff023cfd1",
            "f00ad215-ec4d-42a9-8a8e-a3a82ef223a3",
            "7def84f1-30da-4416-858f-a0cecc2c2960",
            "de10f4d1-86c9-4be2-9cbc-0c147cf9a0c7",
            "214661b0-f73a-49e7-a8f3-5c12426a00e6",
            "966353f6-bc7e-4d41-a813-4b91b811042a",
            "888c3ec5-03c4-4d5a-bf16-25d4508e9153",
            "5ad34d8a-3fc3-4a70-9ce5-248c3d254921",
            "fb8b57b1-6bd2-4f28-9beb-f1ce487c571c",
            "ce04c046-97df-466d-bf80-c10486f6bb2f",
            "8ce63a6e-1ca0-43b5-bca4-cdb446b661c9",
            "900304c0-43ef-4247-8538-50d509119a01",
            "787da7d7-2535-4d34-889d-3862240f7919",
            "8458990e-688f-441a-b645-4202139c8bfb",
            "7a40629a-bdfe-4be4-92cb-59f267890b05",
            "28c851ab-eb36-4bbf-a32b-909a28a29989",
            "0a8cc6dc-a204-4bcc-afcb-45c41d0c55b9",
            "bb5048f5-05ca-42df-8464-b8dc5284300d",
            "a8558705-b661-4057-9d07-fabae6d23024",
            "7a40629a-bdfe-4be4-92cb-59f267890b05",
            "4430a4f1-7e9a-4684-8236-0400322b36a4",
            "96dc74ce-4b7d-477f-a277-f0cd2ee92516",
            "b6496dd5-77e7-4708-9778-3783fe651d3f",
            "3a079e5b-fd5c-49d9-a610-391ee040d6e5",
            "3c3fabea-1000-4611-a50f-32e27882e59c",
            "b82e72db-c6fa-41ba-a995-8d22c54e8d1e",
            "870a3658-fe3e-4a13-9e9f-00e0419bba36",
            "f170781f-6314-47f1-ba0e-964279bf8fbf",
            "7c286eed-b64a-42ee-99c9-1cd3abfef4d7",
            "60c7daf9-fd47-440e-8fdb-e52c8b904716",
            "8f8f6fad-9deb-4f38-8293-1c704c35517e",
            "fd689917-9e14-486a-abd7-bb7c8b6cbf76",
            "ca947d80-698b-4843-9a13-359852dfd684",
            "f8f8740b-1a42-4081-a0aa-bd04cb22008a"
        );


        sjk.stream().forEach(s-> System.out.println(UUID.fromString(s)));




    }

}
