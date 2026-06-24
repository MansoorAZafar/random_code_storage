const toggle = document.querySelector('.toggle')
// We're grabbing the class of toggle
const navigation = document.querySelector('.navigation')

toggle.addEventListener('click', () => {
    toggle.classList.toggle('active');
    // If it doesnt have the class 'active' I want to add it -> if it does have it -> I want to remove it
    navigation.classList.toggle('active');
})

// document.querySelector: Allows us to select something from the DOM
// DOM = Document Object Model
//  -> All your HTML tags, everything used to make the document