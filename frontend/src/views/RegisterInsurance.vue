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
            <v-card-title  style="font-size: 23px">กรมธรรม์</v-card-title>
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
              v-model="RegisterInsurances.Insuranceid"
              :items="insurances"
               label="เลือกกรมธรรม์" 
               item-value="createInsurance_id"
               item-text="createInsurance_name"
               prepend-icon="assignment_turned_in"
               dense outlined>
              </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="d-flex" cols="12" sm="5">
              <v-select
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
                v-model="RegisterInsurances.Titleid"
                :items="title"
                item-value="id"
                item-text="title"
                label="คำนำหน้า"
                prepend-icon = "person"
                 hint="กรณีที่ไม่ใช่ตนเอง"
                outlined
              ></v-select>
            </v-col>
            <v-col cols="12" sm="4">
              <v-text-field outlined
               label="ชื่อ(บุคคลที่ซื้อกรมธรรม์ให้)"
               prepend-icon="clear_all"
               v-model="firstname"
               hint="กรณีที่ไม่ใช่ตนเอง"
               ></v-text-field>
            
            </v-col>
            <v-col cols="12" sm="5">
              <v-text-field    
              outlined label="นามสกุล(บุคคลที่ซื้อกรมธรรม์ให้)"
               prepend-icon="clear_all" 
               hint="กรณีที่ไม่ใช่ตนเอง"
              v-model="surname"
               ></v-text-field>
            
            </v-col>
          </v-row>
          
          <template>
            <div class="text-center">
              <v-btn @click="saveRegisterInsurance" color="indigo" dark large>
                Save
                <v-icon right >mdi-content-save</v-icon>
              </v-btn>
              <v-btn class="ma-10" @click="cancel" color="red darken-1" dark large>
                Cancel
                 <v-icon right >highlight_off</v-icon>
              </v-btn>
            </div>
           </template>
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
      RegisterInsurances: {
        Insuranceid: "",
        Personid: "",
        Titleid: ""
      },
        header: [
        {
          text: 'ชื่อกรมธรรม์',
          align: 'left',
          sortable: false,
          value: "createInsurance_name"
        },
        { text: 'ประเภทกรมธรรม์' , value: "insuranceTypeId.insuranceType_name"},
        { text: 'คุ้มครองอุบัติเหตุ' , value: "createInsurance_accidentCoverage"},
        { text: 'คุ้มครองโรค' , value: "createInsurance_diseaseCoverage"},
        { text: 'สิทธิ์การคุ้มครอง' , value: "createInsurance_protectionRights"},
        { text: 'เงื่อนไขการคุ้มครอง' , value: "createInsurance_termOfProtection"},
        { text: 'วันครบกำหนดสัญญา' , value: "contractDueDateId.contractDueDate_name"},
        { text: 'เบี้ยประกัน' ,        value: "createInsurance_insurancePremium"},
        { text: 'ระยะเวลาชำระเบี้ยประกัน' , value: "paymentPeriodId.paymentPeriod_name"}
        ],
    insurances: [],
    person: [],
    title: [],
    firstname: "",
    surname: ""
    };
  },
  methods: {
     /* eslint-disable no-console */
    getCreateInsurance(){
      
      http
        .get("/CreateInsurance")
        .then(reponse => {
          this.insurances = reponse.data;
         
          console.log( reponse.data);
          
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPerson(){
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
    getTitle(){
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
    saveRegisterInsurance() {
      console.log("save");
      console.log(this.RegisterInsurances.Insuranceid);
      console.log(this.RegisterInsurances.Personid);
      console.log(this.RegisterInsurances.Titleid);
      console.log(this.firstname);
      console.log(this.surname);
      
      http
        .post(
          "/registerInsurance/"+
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
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");
          window.location.reload();
        })
        .catch(e => {
          console.log(e);
        });
    
    },
    cancel(){
      window.location.reload();
    }
  },
  
   mounted() {
      this.getCreateInsurance();
      this.getPerson();
      this.getTitle();
    }
};
</script>