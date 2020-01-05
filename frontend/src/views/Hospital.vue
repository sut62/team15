<template>
  <v-app id="inspire">
    <v-app-bar app clipped-left>
      <v-icon size="30">apartment</v-icon>
      <v-toolbar-title>Register Hospital</v-toolbar-title>
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
          <v-card-text>
            <v-col>
              <v-col>
                <v-row>
                  <v-col class="d-flex" cols="12" sm="8">
                    <v-text-field label="Hospital Name" v-model="HospitalName"></v-text-field>
                  </v-col>
                  <v-col class="d-flex" cols="12" sm="4">
                    <v-select
                      :items="Type"
                      label="Hospital Type"
                      v-model="Hospital.TypeID"
                      item-text="typeName"
                      item-value="id"
                      :rules="[(v) => !!v || 'Item is required']"
                      outlined
                    ></v-select>
                  </v-col>
                </v-row>
                <v-text-field color="white" label="Location Details" v-model="LocationDetails"></v-text-field>
                <v-row>
                  <v-col class="d-flex" cols="12" sm="4">
                    <v-select
                      :items="Region"
                      label="Region"
                      v-model="Hospital.RegionID"
                      item-text="regionName"
                      item-value="id"
                      :rules="[(v) => !!v || 'Item is required']"
                      outlined
                    ></v-select>
                  </v-col>
                  <v-col class="d-flex" cols="12" sm="8">
                    <v-select
                      :items="Province"
                      label="Province"
                      v-model="Hospital.ProvinceID"
                      item-text="provinceName"
                      item-value="id"
                      :rules="[(v) => !!v || 'Item is required']"
                      outlined
                    ></v-select>
                  </v-col>
                </v-row>
                <v-text-field color="white" label="Telephone number" v-model="TelephoneNumber"></v-text-field>
              </v-col>
            </v-col>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-col>
              <v-btn rounded large color="success" @click="save">Save</v-btn>
              <v-btn color="#ffffff" @click="$router.push('/')" text large>cancel</v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-content>

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
      items: [
        { icon: "home", text: "Home", route: "/Main" },
        { icon: "exit_to_app", text: "Sign Out", route: "/" }
      ],
      Hospital: {
        TypeID: "",
        ProvinceID: "",
        RegionID: ""
      },
      Type: [],
      Province: [],
      Region: [],
      HospitalName: "",
      LocationDetails: "",
      TelephoneNumber: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    getType() {
      console.log("HospitalType");
      http
        .get("HospitalType")
        .then(response => {
          this.Type = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
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
    save() {
      console.log("Save");
      http
        .post(
          "/AddHospital/" +
            this.Hospital.TypeID +
            "/" +
            this.Hospital.RegionID +
            "/" +
            this.Hospital.ProvinceID +
            "/" +
            this.HospitalName +
            "/" +
            this.LocationDetails +
            "/" +
            this.TelephoneNumber,
          this.Hospital
        )
        .then(responses => {
          console.log(responses);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getType();
    this.getProvince();
    this.getRegion();
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>