<template>
    <main id="hero p-0">
        <div id="carouselHero" class="carousel slide carousel-fade" data-bs-ride="true" data-aos="fade-down">
            <div class="carousel-inner ratio-21x9">
                <div class="carousel-item active">
                    <picture>
                        <source srcset="../assets/img/hero.png" media="(min-width: 993px)">
                        <source srcset="../assets/img/bg-medicina-preventiva-xs.jpg" media="(max-width: 992px)">
                        <img src="../assets/img/hero.png" alt="..." class="position-relative start-50 translate-middle-x">
                    </picture>
                </div>
            </div>
            
            <div class="carousel-indicators container position-absolute justify-content-center justify-content-lg-start start-50 bottom-0 m-0 translate-middle-x py-5" style="padding-left: calc(var(--flex-gutter-x) * .5) !important;">
                
                <button type="button" data-bs-target="#carouselHero" data-bs-slide-to="0" style="width:38px; height:6px" class="active rounded-3 border-0" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselHero" data-bs-slide-to="1" style="width:38px; height:6px" class="active rounded-3 border-0" aria-current="true" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselHero" data-bs-slide-to="2" style="width:38px; height:6px" class="active rounded-3 border-0" aria-current="true" aria-label="Slide 3"></button>
                
            </div>
        </div>
    </main>
</template>

<style>
    #hero,
    #hero img { height: 100% }

    #hero img { 
    width: 100%;
    object-fit: cover }

    @media(max-width:767.98px){
        #hero h1,
        #hero h2 { text-shadow: 1px 1px 0 rgba(255,255,255,1)}

    }
</style>