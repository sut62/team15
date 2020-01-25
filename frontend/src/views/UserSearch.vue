<template>
  <div>
    <v-toolbar dark color="#ADA8A2" max-width="mx-auto" max-height="100">
      <v-layout justify-center></v-layout>
      <v-toolbar-title class="white--text">ค้นหาข้อมูลสมาชิกประกันสุขภาพ</v-toolbar-title>
      <v-layout justify-center></v-layout>
    </v-toolbar>
    <v-flex >
      <v-card class="desing-card2">
        <v-img

          src="https://kaboompics.com/cache/3/e/e/d/9/3eed948004462069e6296b149bb87a043462208b.jpeg"
          lazy-src="https://picsum.photos/id/11/10/6"
          aspect-ratio="2"
          class="grey lighten-2 "
        >
          <v-form>
            <v-container>
              <br/>
              <br/>
              <br/>
              <br/>
              <v-col width="2000">
                <v-app-bar dark color="#ADA8A2" height="140">
                  <v-col>
                    <v-row>
                      <v-spacer />
                      <v-text-field
                        v-model="userID"
                        label="Search (เลขบัตรประจำตัวประชาชน)"
                        class="mx-4"
                      ></v-text-field>
                      <v-btn dark large @click="getUser()" icon>
                        <v-icon>search</v-icon>
                      </v-btn>
                      <v-spacer />
                    </v-row>
                  </v-col>
                </v-app-bar>

                <v-flex>
                  <div v-if="table">
                    <v-card class="mx-auto" max-width="1200">
                      <div class="cyan darken-1 text-center">
                        <v-layout justify-center></v-layout>
                      </div>
                      <v-data-table hide-default-footer :headers="headers" :items="desserts"></v-data-table>
                    </v-card>
                  </div>
                </v-flex>

                <template>
                  <div class="text-right" >
                    <v-btn
                      class= "mt-10"
                      @click="$router.push('/UserLogin')"
                      color="##55B82"
                      rounded large
                    >BACK</v-btn>
                  </div>
                </template>

                <v-dialog v-model="dialog" max-width="290">
                  <v-card>
                    <br />
                    <v-card-text v-if="status==0">กรุณากรอกข้อมูลในช่อง Search</v-card-text>
                    <v-card-text v-if="status==1">ไม่พบข้อมูล</v-card-text>
                    <v-card-text v-if="status==2">ค้นหาสำเร็จ</v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="#C49A6C" text @click="dialog = false">close</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-col>
            </v-container>
          </v-form>
        </v-img>
      </v-card>
    </v-flex>
  </div>
</template>



<script>
import http from "../api/http-common";
export default {
  name: "UserSearch",
  data() {
    return {
      headers: [
        {
          text: "ชื่อ",
          align: "left",
          sortable: false,
          value: "firstName"
        },
        { text: "นามสกุล", value: "lastName" },
        { text: "วัน เดือน ปี เกิด", value: "birthDay" },
        { text: "อายุ", value: "age" },
        { text: "สถานะภาพ", value: "status.statusName" },
        { text: "อาชีพ", value: "career" },
        { text: "โรคประจำตัว", value: "disease" },
        { text: "เบอร์โทร", value: "phoneNumber" },
        { text: "ที่อยู่", value: "address" },
        { text: "จังหวัด", value: "province.provinceName" },
        { text: "รหัสไปรษณีย์", value: "postalCode" }
      ],
      menu: false,
      BirthDays: new Date().toISOString().substr(0, 10),
      desserts: [],
      userID: "",
      dialog: false,
      status: 0,
      table: false
    };
  },
  /* eslint-disable no-console */
  // ดึงข้อมูล Title ใส่ combobox
  methods: {
    /* eslint-disable no-console */
    getUser() {
      if (this.userID == "") {
        this.status = 0;
        this.dialog = true;

        return;
      }
      http
        .get("/user/" + this.userID)
        .then(response => {
          if (response.data == "") {
            this.status = 1;
            this.dialog = true;
          } else {
            this.desserts = response.data;
            this.status = 2;
            this.table = true;
            this.dialog = true;
          }
        })

        .catch(e => {
          console.log(e);
        });
    }
  },

  mounted() {}
};
</script>  

