<template>
  <v-app>
    <ToolBars></ToolBars>
    <div>
      <v-carousel hide-delimiters>
        <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
      </v-carousel>
    </div>
  </v-app>
</template>

<script>
import ToolBars from "../components/ToolBars";
export default {
  components: {
    ToolBars
  },
  data() {
    return {
      items: [
        {
          src: require("../assets/slide/slide-1.jpg")
        },
        {
          src: require("../assets/slide/slide-2.jpg")
        },
        {
          src: require("../assets/slide/slide-3.jpg")
        },
        {
          src: require("../assets/slide/slide-4.jpg")
        }
      ]
    };
  }
};
</script>

<style>
</style>