regExps = {
"exercise_1": /[A-Z]([a-z])+/,
"exercise_2": /088[1-9]{7}/,
"exercise_3": /[^0-1]+/,
"exercise_4": /^[a-z][^@$]{2,17}$/,
"exercise_5": /^\d{0,3}$|^[1][0-4]\d{2}$|(1500)/,
"exercise_6": /class=["'].*["']/
};
cssSelectors = {
"exercise_1": "item>java",
"exercise_2": ".diffClass",
"exercise_3": ".class1 > .class1.class2",
"exercise_4": "css > item + item + item",
"exercise_5": ".class1.class2 > .class1 + .class1",
"exercise_6": "item > item > item > item > item",
"exercise_7": "#diffId2 > .diffClass + .diffClass",
"exercise_8": "#someId"
};
