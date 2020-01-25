<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left>
      <v-row />
      <v-toolbar-title>Hospital Search</v-toolbar-title>
      <v-spacer />

      <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

 <v-card height="mx-auto" width="250" dark>
          <v-list>
            <v-list-item @click="$router.push('/HomeAdmin')">
              <v-col class="8">
                <v-icon>home</v-icon>
              </v-col>
              <v-list-item-title>Home</v-list-item-title>
            </v-list-item>
                       <v-list-item @click="$router.push('/')">
              <v-col class="8">
                <v-icon>exit_to_app</v-icon>
              </v-col>
              <v-list-item-title>Sign Out</v-list-item-title>
            </v-list-item>
             <v-divider></v-divider>
            <v-list-item @click="$router.push('/Hospital')">
              <v-col class="8">
                <v-icon>apartment</v-icon>
              </v-col>
              <v-list-item-title>Hospital</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/HospitalSearch')">
              <v-col class="8">
                <v-icon>search</v-icon>
              </v-col>
              <v-list-item-title>HospitalSearch</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/CreateInsurance')">
              <v-col class="8">
                <v-icon>add_to_photos</v-icon>
              </v-col>
              <v-list-item-title>CreateInsurance</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/PayInsurance')">
              <v-col class="8">
                <v-icon>call_to_action</v-icon>
              </v-col>
              <v-list-item-title>PayInsurance</v-list-item-title>
            </v-list-item>
             <v-list-item @click="$router.push('/Promotion')">
              <v-col class="8">
                <v-icon>mdi-message-text</v-icon>
              </v-col>
              <v-list-item-title>Promotion</v-list-item-title>
            </v-list-item>
            <v-list-item @click="$router.push('/RenewInsurance')">
              <v-col class="8">
                <v-icon>event</v-icon>
              </v-col>
              <v-list-item-title>RenewInsurance</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card>
      </v-menu>
    </v-app-bar>
    <div
      class="img-background"
      v-bind:style="{'background-image': 'url(' + require('../assets/background1.jpg') + ')'}"
    >
      <v-content>
        <v-col align="center">
          <v-card height="mx-auto" width="1200" dark>
            <v-app-bar height="100" src="../assets/background3.jpg" dark flat>
              <v-col>
                <v-row>
                  <v-spacer />
                  <v-text-field solo-inverted flat hide-details label="Search" v-model="Searching" />
                  <v-btn dark large @click="Search()" icon>
                    <v-icon>search</v-icon>
                  </v-btn>
                  <v-spacer />
                </v-row>
              </v-col>
            </v-app-bar>

            <v-card-text>
              <v-col>
                <v-data-table
                  :headers="headers"
                  :items="GetHospital"
                  :items-per-page="5"
                  class="elevation-1"
                ></v-data-table>
              </v-col>
            </v-card-text>
          </v-card>
        </v-col>

        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <br />
            <v-card-text v-if="status==0">Enter information in the search.</v-card-text>
            <v-card-text v-if="status==1">Not found.</v-card-text>
            <v-card-text v-if="status==2">Successfully</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog = false">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-content>
    </div>

    <v-footer app>
      <v-spacer />
      <span>Health Insurance System &copy; 2020</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../api/http-common";
export default {
  data() {
    return {
      dessert: [],
      Searching: null,
      status: 0,
      dialog: false,
      Hospital: {
        ProvinceID: "",
        RegionID: ""
      },
      headers: [
        { text: "No", value: "id" },
        {
          text: "Hospital Name",
          align: "left",
          sortable: false,
          value: "hospitalName"
        },
        {
          text: "Phone Number",
          align: "left",
          sortable: false,
          value: "telephoneNumber"
        },
        {
          text: "Location Details",
          align: "left",
          sortable: false,
          value: "locationDetails"
        },
        { text: "Province", value: "provinceId.provinceName" },
        { text: "Region", value: "regionId.regionName" },
        { text: "Hospital Type", value: "hospitalTypeId.typeName" }
      ],

      Province: [],
      Region: [],
      GetHospital: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getProvince() {
      console.log("Province");
      http
        .get("/Province")
        .then(response => {
          this.Province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRegion() {
      console.log("Region");
      http
        .get("/Region")
        .then(response => {
          this.Region = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getHospital() {
      console.log("Hospital");
      http
        .get("/Hospital")
        .then(response => {
          this.GetHospital = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    Search() {
      console.log(this.Searching);
      if (this.Searching == "" || this.Searching == null) {
        this.status = 0;
        this.dialog = true;
        this.getHospital();
        return;
      }
      http
        .get("/HospitalSearch/" + this.Searching)
        .then(response => {

          if (response.data== "" || response.data == null) {
            this.status = 1;
            this.dialog = true;
          } else {
            this.GetHospital = response.data;
            this.status = 2;
            this.dialog = true;
          }
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getHospital();
    this.getProvince();
    this.getRegion();
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>