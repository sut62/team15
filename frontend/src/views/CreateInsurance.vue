<template>
  <v-app id="inspire" >
    <v-app-bar app clipped-left
     dark
    color="#A60814">
      <v-toolbar-title style="font-size: 25px">CreateInsurance</v-toolbar-title>
      <v-spacer />
      <v-menu bottom left>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>

        <v-card height="mx-auto" width="200" dark>
          <v-list  disabled>
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
      <v-col cols="5" sm="2" md="7">
              <v-text-field label="ชื่อกรมธรรม์" :rules="[(v) => !!v || 'Item is required']"></v-text-field>
            </v-col>
            <v-col class="d-flex" cols="12" sm="5">
          <v-select
          :items="ประเภทกรมธรรม์"
          label="ประเภทกรมธรรม์"
          dense
          outlined
          :rules="[(v) => !!v || 'Item is required']"
        ></v-select>
          </v-col>
          </v-row>
        </v-container>
     
      

 
        <v-container fluid>
        <v-row>
           <v-col class="d-flex" cols="12" sm="6">
        <v-select
          :items="ราคากรมธรรม์"
          label="ราคากรมธรรม์"
          dense
          outlined
          :rules="[(v) => !!v || 'Item is required']"
        ></v-select>
        </v-col>
        <v-col class="d-flex" cols="12" sm="6">
        <v-select
          :items="ระยะเวลาคุ้มครอง"
          label="ระยะเวลาคุ้มครอง"
          dense
          outlined 
          :rules="[(v) => !!v || 'Item is required']"
          ></v-select>
         </v-col>
          </v-row>
          </v-container>
           

             <v-container fluid>
             <v-row>
            <v-col cols="12" md="6">
            <v-textarea
               name="input-7-1"
               label="รายละเอียดการคุ้มครอง"
               hint="Hint text"
               :rules="[(v) => !!v || 'Item is required']"
             ></v-textarea>
             </v-col>
             </v-row>
             </v-container>
          <v-divider></v-divider>

          <v-card-actions>
            <v-col>
               <v-btn rounded large color="#A60814" @click="$router.push('/')">Save</v-btn>
                <v-btn  @click="$router.push('/')" text large>cancel</v-btn>
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
export default {
  data() {
    return {
      items: [
        { icon: "home", text: "Home", route: "/Main" },
        { icon: "exit_to_app", text: "Sign Out", route: "/" }
      ]
    };
  },

  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>