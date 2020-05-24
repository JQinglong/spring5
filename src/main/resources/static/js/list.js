Vue.component('price', {
    data: function () {
        return {
            items: []
        }
    },
    mounted: function ()  {
        var self = this;
        axios
            .get('https://script.google.com/macros/s/AKfycbxXAasn7gcP7yT3Ir7r08vf63Ta3_zirFxlbg7WxvEA07udmteY/exec')
                .then(function (res) {
                    self.items = res.data;
                });
    },
    template: '<div class="box-inner"><div class="box-content"><table class="table table-bordered table-striped table-condensed">' +
            '<tbody><tr v-for="item in items">' +
            '<td>{{ item.曲名 }}</td><td><pre>{{ item.歌詞 }}</pre></td>' +
            '</tr></tbody></table></div></div>'
});