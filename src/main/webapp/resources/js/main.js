function input_log() {
    // let elements = document.getElementsByTagName("input");
    // for (let i = 0; i < elements.length; i++) {
    //     console.log(elements.item(i));
    // }
    for (let node of document.querySelectorAll('form.input')) {
        console.log(node);
    }
}

input_log();
// alert("pff");

// if (confirm("bla bla")) {
//     alert("ok");
// }
