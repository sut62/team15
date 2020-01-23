<template>
  <div>
    <ToolBars></ToolBars>
    <v-flex class="mt-10">
      <v-card class="mx-auto" max-width="1350">
        <div align="center" justify="center">
          <v-img
            src="https://sv1.picz.in.th/images/2020/01/04/RYmiDk.jpg"
            lazy-src="https://picsum.photos/id/11/10/6"
            aspect-ratio="1"
            class="grey lighten-2"
            max-width="mx-auto"
            max-height="1000"
          >
            <div class="pink darken-1 text-center">
              <v-layout justify-center>
                <v-card-title style="font-size: 25px;" class="white--text">สมัครกรมธรรม์</v-card-title>
              </v-layout>
            </div>
            <v-flex class="mt-5">
              <v-card class="mx-auto" max-width="1000">
                <div class="pink lighten-4 text-center">
                  <v-layout justify-center>
                    <v-card-title style="font-size: 23px">กรมธรรม์</v-card-title>
                  </v-layout>
                </div>
                <v-data-table
                  :headers="header"
                  :items="insurances"
                  :items-per-page="4"
                  class="elevation-1"
                  height="240"
                ></v-data-table>
              </v-card>
            </v-flex>
            <v-container>
              <v-row>
                <v-col class="d-flex" cols="12" sm="5">
                  <v-select
                    id="User"
                    v-model="RegisterInsurances.Userid" 
                    :items="user"
                    label="เลือกเลขบัตรประชาชนของสมาชิก"
                    item-value="id"
                    item-text="userid"
                    prepend-icon="credit_card"
                    dense
                    outlined
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col class="d-flex" cols="12" sm="5">
                  <v-select
                    id="Insuranceselect"
                    v-model="RegisterInsurances.Insuranceid"
                    :items="insurances"
                    label="เลือกกรมธรรม์"
                    item-value="createInsurance_id"
                    item-text="createInsurance_name"
                    prepend-icon="assignment_turned_in"
                    dense
                    outlined
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col class="d-flex" cols="12" sm="5">
                  <v-select
                    id="Person"
                    v-model="RegisterInsurances.Personid"
                    :items="person"
                    label="เลือกบุคคลที่ซื้อกรมธรรม์"
                    item-value="personId"
                    item-text="persontype"
                    dense
                    outlined
                    prepend-icon="assignment_ind"
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col class="d-flex" cols="12" sm="2">
                  <v-select
                    id="Title"
                    v-model="RegisterInsurances.Titleid"
                    
                    :items="title"
                    item-value="id"
                    item-text="title"
                    label="คำนำหน้า"
                    prepend-icon="person"
                    outlined
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="4">
                  <v-text-field
                   id="Firstname"
                    outlined
                    
                    label="ชื่อ(บุคคลที่ซื้อกรมธรรม์ให้)"
                    prepend-icon="clear_all"
                    v-model="firstname"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="5">
                  <v-text-field
                    id="Surname"
                    outlined
                    label="นามสกุล(บุคคลที่ซื้อกรมธรรม์ให้)"
                    prepend-icon="clear_all"
                    v-model="surname"
                  ></v-text-field>
                </v-col>
              </v-row>

              <template>
                <div class="text-center">
                  <v-btn @click="saveRegisterInsurance" color="indigo" dark large>
                    Save
                    <v-icon right>mdi-content-save</v-icon>
                  </v-btn>
                  <v-btn class="ma-10" @click="cancel" color="red darken-1" dark large>
                    Clear
                    <v-icon right>highlight_off</v-icon>
                  </v-btn>
                </div>
              </template>

              <v-dialog v-model="dialog" max-width="290">
                <v-card>
                  
                  <v-card-title class="green  white--text" primary-title>
                       <div class="font-head-desin">การสมัครกรมธรรม์</div>
                    </v-card-title>
                  <v-card-text>
                    <div class="ma-2" style="font-size: 18px;">
                               บันทึกข้อมูลสำเร็จ
                      </div>
                   </v-card-text>
                  <v-card-actions> 
                    <v-spacer></v-spacer>
                    <v-btn color="indigo darken-1" text @click="dialog = false">ยืนยัน</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

              <v-dialog v-model="dialog2" width="500">
                        <v-card>
                          <v-card-title class="red lighten-1 white--text" primary-title>
                            <div class="font-head-desin">การสมัครกรมธรรม์</div>
                          </v-card-title>
                          <v-card-text>
                            <div class="ma-2" style="font-size: 16px;">
                              <v-icon left>highlight_off</v-icon>ข้อมูลไม่ถูกต้อง
                            </div>
                            <div class="ma-2" style="font-size: 16px;">
                              <v-icon left>highlight_off</v-icon>กรุณากรอกข้อมูลใหม่
                            </div>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <div class="font-desing">
                              <v-btn color="error" @click="dialog2 = false">ปิด</v-btn>
                            </div>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
            </v-container>
          </v-img>
        </div>
      </v-card>
    </v-flex>
  </div>
</template>

<script>
import http from "../api/http-common";
import ToolBars from "../components/ToolBars";
export default {
  name: "registerInsurance",
  components: {
    ToolBars
  },
  data() {
    return {
      dialog: false,
      dialog2: false,
      RegisterInsurances: {
        Insuranceid: "",
        Personid: "",
        Titleid: "",
        Userid: ""
      },
      header: [
        {
          text: "ชื่อกรมธรรม์",
          align: "left",
          sortable: false,
          value: "createInsurance_name"
        },
        { text: "ประเภทกรมธรรม์", value: "insuranceTypeId.insuranceType_name" },
        {
          text: "คุ้มครองอุบัติเหตุ",
          value: "createInsurance_accidentCoverage"
        },
        { text: "คุ้มครองโรค", value: "createInsurance_diseaseCoverage" },
        {
          text: "สิทธิ์การคุ้มครอง",
          value: "createInsurance_protectionRights"
        },
        {
          text: "เงื่อนไขการคุ้มครอง",
          value: "createInsurance_termOfProtection"
        },
        {
          text: "วันครบกำหนดสัญญา",
          value: "contractDueDateId.contractDueDate_name"
        },
        { text: "เบี้ยประกัน", value: "createInsurance_insurancePremium" },
        {
          text: "ระยะเวลาชำระเบี้ยประกัน",
          value: "paymentPeriodId.paymentPeriod_name"
        }
      ],
      insurances: [],
       user: [],
      person: [],
      title: [],
      firstname: "",
      surname: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    getCreateInsurance() {
      http
        .get("/CreateInsurance")
        .then(reponse => {
          this.insurances = reponse.data;

          console.log(reponse.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPerson() {
      console.log("getperson");
      http
        .get("/person")
        .then(reponse => {
          this.person = reponse.data;
          console.log(reponse.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTitle() {
      console.log("getTitle");
      http
        .get("/Title")
        .then(reponse => {
          this.title = reponse.data;
          console.log(reponse.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getUser() {
      http
        .get("/User")
        .then(reponse => {
          this.user = reponse.data;

          console.log(reponse.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveRegisterInsurance() {
      console.log("save");
      console.log(this.RegisterInsurances.Insuranceid);
      console.log(this.RegisterInsurances.Personid);
      console.log(this.RegisterInsurances.Titleid);
      console.log(this.firstname);
      console.log(this.surname);

      http
        .post(
          "/registerInsurance/" +
            this.RegisterInsurances.Userid +
            "/" +
            this.RegisterInsurances.Insuranceid +
            "/" +
            this.RegisterInsurances.Personid +
            "/" +
            this.RegisterInsurances.Titleid +
            "/" +
            this.firstname +
            "/" +
            this.surname,
          this.RegisterInsurances
        )
        .then(response => {
          
          this.dialog = true;
         console.log(response);
        })
        .catch(e => {
          this.dialog2 = true;
          console.log(e);
        });
    },
    cancel() {
      window.location.reload();
    }
  },

  mounted() {
    this.getUser();
    this.getCreateInsurance();
    this.getPerson();
    this.getTitle();
  }
};
</script>