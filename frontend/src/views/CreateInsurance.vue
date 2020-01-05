<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left dark color="#A60814">
      <v-toolbar-title style="font-size: 25px">CreateInsurance</v-toolbar-title>
      <v-spacer />
      <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-card height="mx-auto" width="200" dark>
          <v-list disabled>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group v-model="item" color="primary">
              <v-list-item v-for="item in items" :key="item.title" router :to="item.route">
                <v-list-item-icon>
                  <v-icon v-text="item.icon"></v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title v-text="item.text"></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-menu>
    </v-app-bar>

    <v-content>
      <v-col align="center">
        <v-card eight="mx-auto" width="800" dark>
          <v-container fluid>
            <v-row>
              <v-col cols="5" sm="2" md="6">
                <v-text-field label="ชื่อกรมธรรม์" :rules="[(v) => !!v || 'Item is required']"  v-model="Name"></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="insuranceType"
                  label="ประเภทกรมธรรม์"
                  v-model="CreateInsurance.InsuranceType_id"
                  item-text="insuranceType_name"
                  item-value="insuranceType_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
              <v-col cols="5" sm="2" md="6">
                <v-text-field
                  label="คุ้มครองอุบัติเหตุ"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="AccidentCoverage"
                ></v-text-field>
              </v-col>
              <v-col cols="5" sm="2" md="6">
                <v-text-field label="คุ้มครองโรค" :rules="[(v) => !!v || 'Item is required']" v-model="DiseaseCoverage"></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-textarea
                  name="input-7-1"
                  label="สิทธิการคุ้มครอง"
                  hint="Hint text"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="ProtectionRights"
                ></v-textarea>
              </v-col>
              <v-col cols="12" md="6">
                <v-textarea
                  name="input-7-1"
                  label="เงื่อนไขการคุ้มครอง"
                  hint="Hint text"
                  :rules="[(v) => !!v || 'Item is required']"
                  v-model="TermOfProtection"
                ></v-textarea>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="contractDueDates"
                  label="วันครบกำหนดสัญญา"
                  v-model="CreateInsurance.ContractDueDate_id"
                  item-text="contractDueDate_name"
                  item-value="contractDueDate_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>

          <v-container fluid>
            <v-row>
              <v-col cols="5" sm="2" md="6">
                <v-text-field label="เบี้ยประกัน" :rules="[(v) => !!v || 'Item is required']" v-model="InsurancePremium"></v-text-field>
              </v-col>
              <v-col class="d-flex" cols="12" sm="6">
                <v-select
                  :items="paymentPeriods"
                  label="ระยะเวลาชำระเบี้ยประกัน"
                  v-model="CreateInsurance.PaymentPeriod_id"
                  item-text="paymentPeriod_name"
                  item-value="paymentPeriod_id"
                  dense
                  outlined
                  :rules="[(v) => !!v || 'Item is required']"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>

          <v-container fluid>
            <v-row></v-row>
          </v-container>
          <v-divider></v-divider>

          <v-card-actions>
            <v-col>
              <v-btn rounded large color="#A60814" @click="save">Save</v-btn>
              <v-btn @click="cancel" text large>cancel</v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-content>

    <v-footer app color="#A60814">
      <span>&copy; Health insurance system 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../api/http-common";
export default {
  data() {
    return {
      items: [
        { icon: "home", text: "Home", route: "/Main" },
        { icon: "exit_to_app", text: "Sign Out", route: "/" }
      ],
CreateInsurance:{
InsuranceType_id: "",
ContractDueDate_id: "",
PaymentPeriod_id: "",
},

      Name: "",
      AccidentCoverage: "",
      DiseaseCoverage: "",
      ProtectionRights: "",
      TermOfProtection: "",
      InsurancePremium: "",

      insuranceType: [],
      contractDueDates: [],
      paymentPeriods: []
    };
  },

  created() {
    this.$vuetify.theme.dark = true;
  },

  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล RegisterClub ใส่ combobox
    getInsuranceType() {
      console.log("getInsuranceType");
      http
        .get("/InsuranceType")
        .then(response => {
          this.insuranceType = response.data;
          console.log(this.insuranceType);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RegisterClub ใส่ combobox
    getContractDueDate() {
      console.log("getContractDueDate");
      http
        .get("/ContractDueDate")
        .then(response => {
          this.contractDueDates = response.data;
          console.log(this.contractDueDates);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล PositionClub ใส่ combobox
    getPaymentPeriod() {
      console.log("getPaymentPeriod");
      http
        .get("/PaymentPeriod")
        .then(response => {
          this.paymentPeriods = response.data;
          console.log(this.paymentPeriods);
        })
        .catch(e => {
          console.log(e);
        });
    },

   // function เมื่อกดปุ่ม submit
    save() {
      console.log(this.Name);
      console.log(this.AccidentCoverage);
      console.log(this.DiseaseCoverage);
      console.log(this.ProtectionRights);
      console.log(this.TermOfProtection);
      console.log(this.InsurancePremium);

      console.log(this.CreateInsurance.InsuranceType_id);
      console.log(this.CreateInsurance.ContractDueDate_id);
      console.log(this.CreateInsurance.PaymentPeriod_id);
      
      http
        .post(
          "/CreateInsurance/" +
            this.Name +
            "/" +
            this.AccidentCoverage +
            "/" +
            this.DiseaseCoverage +
            "/" +
            this.ProtectionRights +
            "/" +
            this.TermOfProtection +
            "/" +
            this.InsurancePremium +
            "/" +
            this.CreateInsurance.InsuranceType_id +
            "/" +
            this.CreateInsurance.ContractDueDate_id +
            "/" +
            this.CreateInsurance.PaymentPeriod_id,
            this.CreateInsurance
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          this.$router.push('/userlogin')
        })
        .catch(e => {
          console.log(e);
        });
      //this.submitted = true;
    },
    
    cancel(){
      window.location.reload();
    }
   
  },
  mounted() {
    this.getInsuranceType();
    this.getContractDueDate();
    this.getPaymentPeriod();
  }
};
</script>