const slider = document.querySelector('.slider');
const sliderContainer = document.querySelector('.slider-container');
const cards = document.querySelectorAll('.card');
const cardWidth = cards[0].offsetWidth;
let currentPosition = 0;

sliderContainer.style.width = `${cardWidth * cards.length}px`;

document.querySelector('.slider-control-prev').addEventListener('click', () => {
    currentPosition = Math.max(0, currentPosition - cardWidth);
    sliderContainer.style.transform = `translateX(-${currentPosition}px)`;
});

document.querySelector('.slider-control-next').addEventListener('click', () => {
    currentPosition = Math.min(currentPosition + cardWidth, cardWidth * (cards.length - 1));
    sliderContainer.style.transform = `translateX(-${currentPosition}px)`;
});
