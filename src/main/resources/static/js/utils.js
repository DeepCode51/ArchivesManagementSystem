// 数组去重
function unique(ary) {
   let newAry = [];
   for (let i = 0; i<ary.length; i++) {
      if (newAry.indexOf(ary[i]) === -1) {
        newAry.push(ary[i]);
      }
   }
   return newAry;
}
//数组去重

