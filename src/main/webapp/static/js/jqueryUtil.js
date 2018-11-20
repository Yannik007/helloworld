var jqueryUtil = {
    /**
     * 序列化表单到对象
     * @param form 表单对象
     * @param falg 是否对表单中的 引号 做处理 true:处理
     * @returns {___anonymous7839_7840}
     */
    serializeObject : function (form, falg) {
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + (this['value'] == '' ? '' : this['value']);
        } else {
            if (falg) {
                var value = this['value'];
                if (value && !value == '' && !value == "" && value != "undefined") {
                    o[this['name']] = value;
                }
            } else {
                o[this['name']] = this['value'] == '' ? '' : this['value'];
            }
        }
    });
    return o;
}

}