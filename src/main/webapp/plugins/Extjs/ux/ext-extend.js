Ext.define('Ext.ux.ComboPageSize', {
    requires: [
        'Ext.form.field.ComboBox'
    ],
    pageSizes: [10, 25, 30, 50, 100, 200, 300, 400],
    constructor: function (config) {
        if (config) {
            Ext.apply(this, config);
        }
    },
    init: function (pbar) {
        var combo,
            me = this;
        combo = Ext.widget('combo', {
            width: 70,
            editable: false,
            store: me.pageSizes,
            listeners: {
                change: function (s, v) {
                    pbar.store.pageSize = v;
                    pbar.store.loadPage(1);
                }
            }
        });
        pbar.add(0, '-');
        pbar.add(0, combo);
        combo.setValue(pbar.store.pageSize);
    }
});