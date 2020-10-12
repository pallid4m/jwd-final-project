function input_log() {
    let elements = document.getElementsByTagName("input");
    for (let i = 0; i < elements.length; i++) {
        console.log(elements.item(i));
    }
}

input_log();
