<template>
  <div id="container" class="">
    <div id="sectionOneScrollArea" class="h-[800rem]">
      <section ref="sectionOne" class="h-screen absolute flex justify-center items-center w-screen">
        <div class="*:text-8xl flex flex-col justify-center h-scree">
          <p v-for="(content, index) in ACT_ONE_CONTENT" v-text="content" :key="index" :ref="pushSectionOneRefs"
            class="text-center font-bold text-darkColor4">
          </p>
        </div>
        <div ref="trigger1" class="absolute top-[100vh]"></div>
        <div ref="horizonalScroll" class="absolute top-[40vh] right-full h-1/2 w-[2000px] *:flex-grow flex">
          <div v-for="(content, index) in ACT_TWO_CONTENT" v-text="content" :key="index"
            class="text-center font-bold text-darkColor4"></div>
        </div>
        <div ref="trigger2" class="absolute top-[360rem]"></div>
        <div :ref="pushPictureTriggerRefs" v-for="(trigger, index) in PICTURE_FRAME_SRC" :key="index"
          :class="`absolute`" :style="`top: ${400 + 50 * index}rem;`"></div>
        <div ref="photoFrame" class="absolute top-[40vh] w-1/3 h-1/3">
          <div :ref="handleImgRef" v-for="(src, index) in PICTURE_FRAME_SRC" :key="index"
            class="border-[1.5rem] absolute opacity-0">
            <img :src="src" class=" rounded-lg">
          </div>
        </div>
        <div ref="trigger3" class=" absolute top-[750rem]">trigger3</div>
      </section>
    </div>

  </div>
</template>

<script setup>
import Lenis from 'lenis';
import 'lenis/dist/lenis.css'
import { onMounted, ref, reactive } from 'vue';
import gsap from 'gsap';

// Initialize Lenis
const lenis = new Lenis({
  autoRaf: true,
  smoothTouch: true
});


const ACT_ONE_CONTENT = reactive(['I am a', 'Web developer', 'and', 'Photographer'])
const ACT_TWO_CONTENT = reactive(['2023·7-java basic', '2023·9-java ', '2023·12-javaweb', '2024·3-累了', '2024·6-SSM'])
const PICTURE_FRAME_SRC = reactive([
  'src\\assets\\introPictures\\1.jpg',
  'src\\assets\\introPictures\\2.jpg',
  'src\\assets\\introPictures\\3.jpg',
  'src\\assets\\introPictures\\4.jpg'
])
const pictureElements = reactive([])
function handleImgRef(el) {
  pictureElements.push(el)
}

onMounted(() => {
  gsap.set(pictureElements, {
    opacity: 0
  })
})
// const rowText = ref('..')
// const dynamicDot = ref()

// dynamicContent(rowText, dynamicDot)

// function dynamicContent(row, current) {
//   const i = ref(0)
//   setInterval(() => {
//     if (i.value > row.value.length) {
//       i.value = 0
//     }
//     current.value = row.value.substring(0, i.value)
//     i.value++
//   }, 500)
// }



const sectionOneRefs = reactive([])

function pushSectionOneRefs(el) {
  sectionOneRefs.push(el)
}
// 入场动画
onMounted(sectionOneAnimation => {
  gsap.from(sectionOneRefs[0], {
    y: '-100vh',
    duration: 1,
    delay: 0.2
  })

  gsap.from(sectionOneRefs[1], {
    x: '100vw',
    duration: 1,
    delay: 0.2
  })

  gsap.from(sectionOneRefs[2], {
    x: '-60vw',
    duration: 1,
    delay: 1.4
  })

  gsap.from(sectionOneRefs[3], {
    y: '100vh',
    duration: 1,
    delay: 1.4
  })
})



import ScrollTrigger from 'gsap/ScrollTrigger';
gsap.registerPlugin(ScrollTrigger)

const sectionOne = ref()
const trigger1 = ref()
const horizonalScroll = ref()
const trigger2 = ref()
const photoFrame = ref()
const trigger3 = ref()
const pictureTriggers = reactive([])
function pushPictureTriggerRefs(el) {
  pictureTriggers.push(el)
}

onMounted(toDevelopPage => {
  lenis.on('scroll', function relativeMoveSection(e) {
    sectionOne.value.style.top = `${e.actualScroll}px`
  });
  // I am 和 and退场
  gsap.fromTo([sectionOneRefs[0], sectionOneRefs[2]],
    {
      scrollTrigger: {
        trigger: trigger1.value,
        start: "top bottom",
        end: "bottom top",
        scrub: 1,
      }
    },
    {
      opacity: 0,
      scrollTrigger: {
        trigger: trigger1.value,
        start: "top bottom",
        end: "bottom top",
        scrub: 1,
      }
    });
  // Web Developer上移放大
  gsap.to(sectionOneRefs[1], {
    y: '-20vh',
    scale: 1.3,
    scrollTrigger: {
      trigger: trigger1.value,
      start: "top bottom",
      end: "bottom top",
      scrub: 1,
    }
  });
  // Photographer退场
  gsap.fromTo(sectionOneRefs[3],
    {
      scrollTrigger: {
        trigger: trigger1.value,
        start: "top bottom",
        end: "bottom top",
        scrub: 1,
      }
    },
    {
      y: '100vh',
      scrollTrigger: {
        trigger: trigger1.value,
        start: "top bottom",
        end: "bottom top",
        scrub: 1,
      }
    })

  lenis.on('scroll', function horizonalScrolling(e) {
    horizonalScroll.value.style.right = `${e.actualScroll - 2800}px`
  });
  // Web Developer退场
  gsap.fromTo(sectionOneRefs[1],
    {
      scrollTrigger: {
        trigger: trigger2.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      y: '-100vh',
      scrollTrigger: {
        trigger: trigger2.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    }
  )
  // Photographer上移放大
  gsap.fromTo(sectionOneRefs[3],
    {
      scrollTrigger: {
        trigger: trigger2.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      y: '-42vh',
      scale: 1.3,
      scrollTrigger: {
        trigger: trigger2.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    })
  // 轮播照片
  // 显示相框
  gsap.to(photoFrame.value, {
    opacity: '100%',
    scrollTrigger: {
      trigger: pictureTriggers[0],
      start: 'top bottom',
      end: 'bottom top',
      scrub: 1
    }
  })
  // 显示第一张照片
  gsap.fromTo(pictureElements[0],
    {
      opacity: '0%',
      scrollTrigger: {
        trigger: pictureTriggers[0],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      opacity: '100%',
      scrollTrigger: {
        trigger: pictureTriggers[0],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    })

  // 切换第二张
  gsap.fromTo(pictureElements[0],
    {
      opacity: '100%',
      scrollTrigger: {
        trigger: pictureTriggers[1],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      opacity: '0%',
      scrollTrigger: {
        trigger: pictureTriggers[1],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    })
  gsap.to(pictureElements[1], {
    opacity: '100%',
    scrollTrigger: {
      trigger: pictureTriggers[1],
      start: 'top bottom',
      end: 'bottom top',
      scrub: 1
    }
  })

  // 切换第三张
  gsap.fromTo(pictureElements[1],
    {
      opacity: '100%',
      scrollTrigger: {
        trigger: pictureTriggers[2],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      opacity: '0%',
      scrollTrigger: {
        trigger: pictureTriggers[2],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    })
  gsap.to(pictureElements[2], {
    opacity: '100%',
    scrollTrigger: {
      trigger: pictureTriggers[2],
      start: 'top bottom',
      end: 'bottom top',
      scrub: 1
    }
  })
  // 切换第四张
  gsap.fromTo(pictureElements[2],
    {
      opacity: '100%',
      scrollTrigger: {
        trigger: pictureTriggers[3],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      opacity: '0%',
      scrollTrigger: {
        trigger: pictureTriggers[3],
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    })
  gsap.to(pictureElements[3], {
    opacity: '100%',
    scrollTrigger: {
      trigger: pictureTriggers[3],
      start: 'top bottom',
      end: 'bottom top',
      scrub: 1
    }
  })
  // Photographer退场
  gsap.fromTo(sectionOneRefs[3],
    {
      scrollTrigger: {
        trigger: trigger3.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    },
    {
      y: '-100vh',
      scrollTrigger: {
        trigger: trigger3.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    }
  )
  // 画框上移反转，结束
  gsap.to(photoFrame.value,
    {
      y: '-20vh',
      rotateY: '180',
      scrollTrigger: {
        trigger: trigger3.value,
        start: 'top bottom',
        end: 'bottom top',
        scrub: 1
      }
    }
  )
})
</script>

<style scoped></style>
