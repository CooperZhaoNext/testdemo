package org.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class SuggestAnalyzeTest {


    static class SuggestionResult {

        private String realSugLoadId;

        private boolean loadStartedSugLoadId;

        private boolean swapLoadSugLoadId;

        public SuggestionResult(String realSugLoadId,boolean loadStartedSugLoadId,boolean swapLoadSugLoadId){
            this.realSugLoadId = realSugLoadId;
            this.loadStartedSugLoadId = loadStartedSugLoadId;
            this.swapLoadSugLoadId = swapLoadSugLoadId;
        }

        public String getRealSugLoadId() {
            return realSugLoadId;
        }

        public void setRealSugLoadId(String realSugLoadId) {
            this.realSugLoadId = realSugLoadId;
        }

        public boolean isLoadStartedSugLoadId() {
            return loadStartedSugLoadId;
        }

        public void setLoadStartedSugLoadId(boolean loadStartedSugLoadId) {
            this.loadStartedSugLoadId = loadStartedSugLoadId;
        }

        public boolean isSwapLoadSugLoadId() {
            return swapLoadSugLoadId;
        }

        public void setSwapLoadSugLoadId(boolean swapLoadSugLoadId) {
            this.swapLoadSugLoadId = swapLoadSugLoadId;
        }
    }

    public static void main(String[] args) throws IOException {

        Map<String, String> realSugMap = Maps.newHashMap();

        Map<String, List<String>> loadStartedSugMap = Maps.newHashMap();

        Map<String, List<String>> swapLoadSugMap = Maps.newHashMap();

        List<String> container = Lists.newArrayList(
            "id",
            "7b39bacb-f97c-42b1-92d5-8663c0661186",
            "24ea1a4c-7a02-4bf0-ae74-f81b560821d7",
            "a0a87c0d-241f-44bc-9b9e-2bd91a400409",
            "4342536f-10f7-4381-8b61-84fbf3170dde",
            "643be5a2-e74e-4ad7-8f36-0393312593e2",
            "a279417e-4a70-47af-b515-d5fa81b7091d",
            "08022c97-cd8e-4996-8738-bc587b8adcd2",
            "f5acdbdd-4523-4a03-9f8a-82b29f1e6a33",
            "bf192f03-7d34-4859-b3a2-64f6d7d09554",
            "c68409f3-7b23-468a-8d7e-4e65b8009b48",
            "a0d8eae4-4ec5-4479-9d88-bb064bf4ee4d",
            "6ec7b025-43e9-4e5d-966c-7d4a6180d43c",
            "d4687c9d-12b2-43a2-b5e8-d570e74b2898",
            "0fa7654e-9c78-4774-9f8f-b02d861bb9fe",
            "95f451bc-e812-4652-8f97-93ebbe0ec2fe",
            "84034a80-d8e2-4407-b174-2d779c90bdc2",
            "cf8d1b66-6648-43b8-8a26-3fda6bd06871",
            "daec08e0-95bf-40c0-aae2-dc64b1de0531",
            "03067e20-93c3-4193-a9e6-d9b98726cebe",
            "72ee96d5-c4d0-48ec-a033-5e074c480a89",
            "46fd1797-da1f-4ce2-86e7-f6f029d399ad",
            "6c0667a9-f42d-4470-90b2-f2d015036f39",
            "daaba464-52b8-4f47-85c1-6b9b9f2f27db",
            "eb4b90ba-c459-46bc-ab71-f4292c09cdd1",
            "69403351-acf7-4ad3-b41a-f02156d45ecb",
            "5eb7e344-bb55-48b6-b778-b9a4e713f6e4",
            "9063d9da-1ebc-4524-b766-7a429c9554e5",
            "935ca188-71b6-4dd2-8e30-3df2f6b44470",
            "616cadf8-55d4-4ef4-a250-8b331873dcb8",
            "c5ab4d37-5a04-4485-9f7d-e349f8fb63b5",
            "906c865d-fc6f-4b83-b8ee-5c08f13fdfc5",
            "cd4cdd35-2b1a-4308-9aec-7ca0c693e646",
            "b65b917d-eec0-4404-84eb-b34c22530e29",
            "56bf1efb-94c1-4038-9b16-863f4074df11",
            "c58ff23d-f417-4041-b48b-840f53a3424d",
            "13a5869e-70d4-46b0-8929-504c9bb00df4",
            "24213b1d-5f04-416b-8a91-e60ab6537c12",
            "fbab4583-e73f-4d0b-8b91-65d9c3264b4f",
            "01ae2bb0-01fb-46b7-8a4c-286fdc4fd636",
            "a9e9fbaf-f722-4653-86c2-cb4b9ee91c05",
            "ccf4a715-f8ae-419b-b8f0-a58f66732968",
            "736ec2f9-1ab0-416e-acb0-e0e3b052b50f",
            "52e4aff7-ba1d-4ccc-8b1f-36e8e32a952d",
            "b1c60ac7-4398-473f-9f8d-40d06ec1202a",
            "50addd10-1ea2-40df-9bbb-da0873b03f99",
            "72794e9f-a6aa-498c-bbd3-4920811fc62c",
            "21fd6a00-7212-4936-bc07-1bb3ea71f129",
            "c5926dce-3f1d-402c-92ac-4ec5e90f5b61",
            "bb0083d3-0dd6-4e5e-a7c7-401742c96316",
            "f9cba311-6f49-4785-9f20-c32e5692e8d5",
            "21fcd5b4-72b4-4080-8288-a31a8b83cb79",
            "64581d57-05a2-46aa-a214-c77e71348052",
            "bfef7407-4a03-449f-9413-3bb94e518d42",
            "ca30c00d-ba88-4a8e-9134-481114392eae",
            "c29631ab-bc1d-47b0-9c6e-0d0d0f7365d9",
            "e0fb8763-3409-436d-bce0-4c49af8f670e",
            "9142b1a2-993c-4540-8e68-312a29adb618",
            "d587c851-1fdd-4b59-9dab-9242e17f7001",
            "e8dd4db4-b7d6-456b-9c85-ebad437da966",
            "63483b12-37df-4d9a-8f47-9d669c7dabc6",
            "e88dc9eb-3ec9-48ea-8a8f-c6ce065a7af5",
            "2eaa8907-0a79-4149-9b8a-a16a1f6232dc",
            "a5a360cb-8483-4d6e-918a-df53f7d6bafc",
            "838b1429-4521-4348-a64d-5d645624c402",
            "413543ed-87b4-4603-9990-dc8884daa35b",
            "1019912c-0c18-4fa3-8e0a-e49b41026c8c",
            "0cc97497-afeb-4ead-991c-e1e2763a020d",
            "cb1d0e85-eb24-43ef-8676-995e580bcb3f",
            "2ba0de67-f05f-44cb-80a4-bc1cd36d9d8e",
            "b6e43bc0-4700-4175-8a01-ef22ca1f3265",
            "f1ddc01e-efec-42e7-a0ad-1f2e0c13f1dd",
            "0ec1af19-0cd6-4f19-ab76-bdb7a7a0d98a",
            "ffb26bb5-1454-43f4-b97c-3fdf8e6d8d8a",
            "1543a6e5-77bc-4e9e-bb0b-cfb43926b0fb",
            "9422a4fd-b652-4d40-b253-6a73bdd20265",
            "da1c0e29-659c-4cb6-8149-d36777ee90be",
            "292f8585-76b3-4271-a12b-2ee20134a741",
            "1a3f86ca-6468-4c18-82e1-9df93c0cd491",
            "c471f6f6-743c-4322-90f2-d8005d55f08e",
            "86fd7914-1c34-439e-9fac-43bc98f014d6",
            "03c7383f-a94a-432c-9ff0-d4b8e37178ac",
            "9a9bb835-842f-436c-83c1-4e1f06e7bd4d",
            "c65fed4d-7251-4315-a6af-c1666f081742",
            "a3bb5777-866f-4f5e-9563-e37f5fff8995",
            "06306c98-7d2a-439c-924b-f8243d408afa",
            "304ce6e7-a1c1-4bbc-8341-f5759fae4e44",
            "49e46416-0615-4b17-8f04-0a7e94028780",
            "83a883cb-b3c0-4117-80cd-325aa8c9a1c3",
            "dc71aef0-8959-4537-91eb-6412823b1bd6",
            "06fa55d0-aa6f-4e62-833e-dae363fc4364",
            "d7ca9489-3213-46d5-8b3b-033c7a4f5c7e",
            "1fe40b23-a722-4adb-a92e-317954cddcdc",
            "859df1a2-a955-4262-a902-ed5ee6745cb7",
            "7419de41-10e9-4688-bb23-31ebc6913252",
            "2d149fd4-409d-4113-b7cf-2cb77d41c9f1",
            "4afdac99-d6e5-4181-9fe1-da3f64ff0984",
            "ba3ecd31-f201-4905-b5f0-d1c679908fcf",
            "88fb35c4-4472-4317-90d6-3ce04b6b725f",
            "7a5c0d90-342b-49d8-8b53-0b8835cc2e96",
            "64ce9bb2-1c95-448e-bce3-641f18890de9",
            "2cd21870-4f12-4055-a411-6d004d7802d0",
            "402fb0eb-049c-40e5-81a3-0ce89c7bd9fc",
            "bd3b7fb2-2f5b-426a-be1f-63f909da0ad7",
            "1733fbc4-317d-4944-b776-f96d676321f2",
            "41a8a15e-e128-4b3f-8630-f82dd179277e",
            "1127ddc1-e246-40ba-b843-ed196870b1f7",
            "bb649494-3fe2-465f-9b7b-18742537a0a7",
            "d57f0764-5e00-444e-a6da-063b004e1077",
            "d0f81f3c-eaf5-4269-bce3-ae827c53e1a2",
            "d0e1986b-b26f-4e7e-a290-d1eb55526d3f",
            "c302ad23-0117-441b-9b8e-6b5e02334229",
            "18308fe5-461e-43ad-9383-a93529719e29",
            "26b132fe-b959-47fb-80ec-e67798ac9d80",
            "c2991d56-19f3-4619-9151-f30a29bbd271",
            "3d6e74fb-3ff8-415f-b3bc-e41f047c3daf",
            "37dfd5c5-3622-4f46-bbcb-57a340b43043",
            "1450acfd-1d3c-4be2-9a01-447660af573d",
            "466d1fcf-1f4f-4491-a313-5a81e29c0c9b",
            "e13fb59c-0a61-4629-bd4b-88ba8f6c3529",
            "11d45502-9af9-4c1d-9fb4-bc7aef2a0eef",
            "ef4dcf11-fa00-4501-89ba-2bcaa9870139",
            "128ee20c-c790-416f-bdcd-abc0b9db9f7c",
            "14fef25e-054f-4534-8a12-eb30aed6bbda",
            "8606ab79-8209-424f-8c25-18561dcec722",
            "372bd41f-e2ea-4d64-b2d9-af87eae54a97",
            "8a42fab7-27ce-490b-ac60-0f3e72502078",
            "371e8b6e-df3c-49ec-817b-c61154670c19",
            "f8e1eaab-f1e9-4b4f-b21f-0842508acc96",
            "c7724538-2022-43c2-b8d3-a5c873efbda5",
            "adfcf75c-7193-46ff-b795-f9fa88929036",
            "b2b4af75-0dbf-49bf-95fe-22051ac8892a",
            "e620e702-9cef-426b-a117-3e216b5a6f9f",
            "f0b81654-1d0c-4eb4-bd7b-af3400d14f35",
            "e1e7fbbc-d2af-4a11-a94b-d93bde294230",
            "c22342fa-7eb8-4834-b5d0-9936edc8c786",
            "74e596bc-a939-4ed1-893e-5ec1c28cb272",
            "56ed401d-6632-4846-b0eb-e9750894c661",
            "78fd51ad-b5cc-4da9-b0ed-ac6936835371",
            "c6798cf0-bcbe-4a57-ae11-ea1f03046685",
            "100a0eb8-2dca-4cc9-bd45-89686ff749e4",
            "0d06b596-fd9f-43aa-b9bf-6b2f71bd1118",
            "78cb0e5a-ad64-46c3-abd1-c84814142224",
            "5c3f6829-6f08-445f-bcf8-f9214205d17e",
            "b0a88300-848c-401c-98f3-3dbb9ae9f450",
            "bbba0256-4ca0-4cf0-b425-a6f3ee3247c6",
            "9d58c721-c302-4781-b847-ad13f16c502a",
            "b093336e-5e96-4060-aec8-6fc3f8cf0e8b",
            "1df243c0-70c8-4b3d-bcac-fef416cd4281",
            "d40f9000-57f7-40ff-9611-b47368213e3d",
            "e12bb2b5-7d3a-4910-810c-3dfd2936d6ea",
            "34c45a3c-2783-4edf-b8e4-54603bd7a981",
            "47794b69-3274-4f7c-b80b-517a2a13b3d0",
            "1a9d1156-4a91-4e34-aefd-9f4e5b970b2c",
            "f24b6049-8cbc-475a-8d85-42017c76e5ed",
            "42fb4bdd-04b9-432e-b8f2-c3d561183238",
            "560540ef-7511-4bcf-b052-105e490e3f4d",
            "7abe4f9a-8ae9-46a4-8917-ff7516ecf849",
            "acf55baf-682f-4fa2-a7a0-257618f411be",
            "11fbed02-8b6e-45f9-91a6-3124bc7dcfda",
            "e8570565-f03a-44a4-ab51-5eb052ca069b",
            "e9181cad-18dd-4cdf-bce8-83a81b082f66",
            "4071f730-ad7b-472a-9993-77fbe13794e1",
            "119d3666-ed5b-4193-a8d5-63220a727b7d",
            "a3fde533-e167-4a9c-a50d-cad76169c14a",
            "0ff7bc03-870b-4179-a8c5-dacbfa781451",
            "693148e2-9789-4c05-b7ed-79f3180e5a37",
            "8ac25a85-d049-4e95-a0e3-9c22aad89a0b",
            "c6b350aa-24e4-4c22-a0d6-9f77c3f97827",
            "93a93f87-5311-46fb-9f19-a3d79b26191a",
            "ad6a1d11-aff3-45da-bfd9-0e175d932eb7",
            "6ee46d94-6f1e-4498-95b4-c385408481b4",
            "69cb9cb9-4d1f-4b3e-ad4e-bc10c5a7cf6c",
            "dac4e117-fa6f-4751-be76-e8244596ab98",
            "928974b2-eeee-466a-b492-9aee02129763",
            "272931e8-f5c1-44e5-864e-c895170792f7",
            "40d614a4-7c6c-4ffb-900a-679dcf59f461",
            "6052d905-1392-4211-aaff-8298b6766026",
            "50a28f8f-6786-45dc-92d6-9e8e15f8653a",
            "4ad6ec59-3f13-42ca-989c-4499aa5557b2",
            "e89fb50d-76fb-45e4-8481-ba404630f8fe",
            "18f6ecf5-4dd9-4eaa-a48d-f2a91e2d6924",
            "e5c3761c-974c-4ae5-9142-5df25889db47",
            "56558c51-f2bf-454e-b2f5-e960d31985eb",
            "c5ec21d0-0790-4490-981f-9ecad759ef92",
            "92a58cdd-e6e8-4b62-aa31-621724b686ab",
            "7ef6d81d-d2f7-47ed-87f3-32fd02aad0c3",
            "eaff86d2-a277-4f48-b690-58a0ad648b7b",
            "42412a0e-c1c8-4c0e-82cf-b616529a5f5f",
            "a0e30520-b8ab-44df-b8de-44791e79926a",
            "395d5cf8-2ef1-453e-b553-6674c5297e4f",
            "f83b3304-ff83-4f52-b793-8d17c2317a5f",
            "536848b8-2fd2-4a04-918c-75575d22202f",
            "a3579445-7141-400a-bfa9-14b1d0bd62a5",
            "e08ff58d-968d-4bd1-ad6f-6415b5cc5672",
            "9b26fadc-9295-460f-8d94-c2cb6cdfdca8",
            "c02c4987-08ab-4a67-9e53-b2f024f4cf52",
            "f571543e-59b3-4891-9f16-e0cd6e6dff02",
            "8a5f0e4f-c03e-457a-93fb-c87f303178b0",
            "3e3ba161-9da3-4217-9c40-c6d01596ff7d",
            "b9f1d9b3-6426-477e-9d8c-9ce6b9d301f6",
            "1724d32f-d6c4-434c-962d-8a355bc452fc",
            "bae056a8-3d85-4967-b2a7-be38b1a87e3d",
            "5bea2a1a-9bc1-4dd4-bddc-7f148faa6493",
            "24b55c57-d609-48fb-8458-c7d7d53cb1cc",
            "e3a387a5-e670-465f-b7a4-4a590be68b9c",
            "f754ae10-c4a4-4538-9f2f-314ce941cc49",
            "8dc13aa9-3235-4d58-8db9-2ff4e4e1ed90"

        );



        List<String> lines = FileUtils.readLines(new File("/Users/cooper.zhao/Desktop/pdf/syggest3.txt"), "UTF-8");

        for (String line : lines) {
            List<String> units = Splitter.on(",")
                .splitToList(line)
                .stream()
                .filter(u -> !u.equals(""))
                .collect(Collectors.toList());

            String first = units.get(0);

            if (first.equals("suggestedSwap")) {
                String third = units.get(2);
                if (third.equals("LOAD_STARTED")) {
                    if (units.size() == 3) {
                        loadStartedSugMap.put(units.get(1), Lists.newArrayList());
                    }
                    loadStartedSugMap.put(units.get(1), Lists.newArrayList(units.subList(3, units.size())));
                }

                if (third.equals("SWAP_PLACEHOLDER_LOAD")) {
                    if (units.size() == 3) {
                        swapLoadSugMap.put(units.get(1), Lists.newArrayList());
                    }
                    swapLoadSugMap.put(units.get(1), Lists.newArrayList(units.subList(3, units.size())));
                }
            }
            if (first.equals("RealSwap")) {
                realSugMap.put(units.get(1), units.get(2));
            }
        }

        List<SuggestionResult> suggestionResults = Lists.newArrayList();

        for(Map.Entry<String,String> entry:realSugMap.entrySet()){
            String ploadId = entry.getKey();

            String realLoadId = entry.getValue();

            List<String> sugStartedIds = loadStartedSugMap.get(ploadId);

            List<String> sugSwapIds = swapLoadSugMap.get(ploadId);

//            if(sugStartedIds == null || sugSwapIds == null){
//                continue;
//            }

            SuggestionResult suggestionResult = new SuggestionResult(ploadId,sugStartedIds!=null && sugStartedIds.contains(realLoadId),sugSwapIds != null && sugSwapIds.contains(realLoadId));

            suggestionResults.add(suggestionResult);

            if(container.contains(ploadId)){
                System.out.println(ploadId+"\t"+suggestionResult.loadStartedSugLoadId+"\t"+suggestionResult.swapLoadSugLoadId);

            }


        }

        System.out.println(suggestionResults.stream().filter(s->s.isLoadStartedSugLoadId()).count());
        System.out.println(suggestionResults.stream().filter(s->s.isSwapLoadSugLoadId()).count());
        System.out.println(suggestionResults.size());

    }

}
