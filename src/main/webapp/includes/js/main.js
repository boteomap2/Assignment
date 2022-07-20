$.get("header.html", function (data) {
    $("my-header").replaceWith(data);
});

$.get("footer.html", function (data) {
    $("my-footer").replaceWith(data);
});

let data = [
    {
        id: 0,
        name: "Item 0",
        price: "$0",
    },
    {
        id: 1,
        name: "Item 1",
        price: "$1",
    },
    {
        id: 2,
        name: "Item 2",
        price: "$2",
    },
    {
        id: 3,
        name: "Item 3",
        price: "$3",
    },
    {
        id: 4,
        name: "Item 4",
        price: "$4",
    },
    {
        id: 5,
        name: "Item 5",
        price: "$5",
    },
];

let $table = $("#table");

$(function () {
    $table.bootstrapTable({
        locale: "en-US",
        data: data,
        columns: [
            {
                title: "ID",
                field: "id",
                sortable: true,
                switchable: false,
            },
            {
                title: "Item Name",
                field: "name",
                sortable: true,
            },
            {
                title: "Item Price",
                field: "price",
                sortable: true,
            },
            {
                field: "operate",
                title: "Item Operate",
                align: "center",
                clickToSelect: false,
                events: window.operateEvents,
                formatter: operateFormatter,
            },
        ],
    });
});

function operateFormatter(value, row, index) {
    return [
        '<a class="update mr-2" href="javascript:void(0)" title="Like">',
        '<i class="far fa-edit fa-2x fa-fw"></i>',
        "</a>  ",
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="far fa-trash-alt fa-2x fa-fw"></i>',
        "</a>",
    ].join("");
}

let myIndex = 0;
window.operateEvents = {
    "click .update": function (e, value, row, index) {
        // alert("You click like action, row: " + JSON.stringify(row));
        $("#machineid").val(row.id);
        $("#machineposition").val(row.name);
        $("#machinestatus").val(row.price);
        $("#updatemodal").modal("show");
        myIndex = index;
    },
    "click .remove": function (e, value, row, index) {
        $("#deleteModal").modal("show");
        myIndex = index;
    },
};

$("#modalsavebtn").on("click", function () {
    let row = {
        id: $("#machineid").val(),
        name: $("#machineposition").val(),
        price: $("#machinestatus").val(),
    };
    $("#updatemodal").modal("hide");
    $table.bootstrapTable("updateRow", {
        index: myIndex,
        row: row,
    });
});

$("#modalDeleteBtn").on("click", function () {
    $table.bootstrapTable("remove", {
        field: "$index",
        values: [myIndex],
    });
    $("#deleteModal").modal("hide");
});
