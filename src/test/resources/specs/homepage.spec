@objects
   ml_logo                 css      .ml-logo
   ml_navigation_section   css      .ml-navigation-section
   ml-header               id       ml-header
   all_filter-*            css      li[class^='ml-lesson-filter-btn']
   ml-lesson-cards-fiters  css      .ml-lesson-cards-fiters
   js-login-btn            css     .js-login-btn
   js-signup-btn           css     .js-signup-btn
   ml_lesson_card-*        css     .ml-lesson-card
   filter_count-*          css     .ml-filter-count
   ml-header-search        css     .ml-header-search

=Ml_home_page=

# check lesson filter section alignment
    ml-lesson-cards-fiters:
          image file  dump/demo/objects/ml-lesson-cards-fiters.png,ignore-objects [filter_count-*]
          
# check ML logo is at top left corner of the screen
    ml_logo:
        inside  screen  0px top left
# check the navigation section is right of the logo and text is Explore All Lessons
    ml_navigation_section:
         right-of ml_logo
         text is "Explore All Lessons"
# check search box is inside the header section and right of navigation section
    ml-header-search:
          inside  ml-header
          right-of    ml_navigation_section
# check all filters are aligned horzontally
    @forEach [all_filter-*] as menuItem, next as nextItem
         ${menuItem}:
                left-of ${nextItem} ~1px
                aligned horizontally all ${nextItem}

# check login buttons are aligned horizontally and its property
    js-login-btn:
           near all_filter-3 ~35px top
           aligned horizontally all js-signup-btn
           text is "Login"
            css background-color is "rgba(62, 157, 230, 1)"

# check signup buttons are aligned horizontally and its property
    js-signup-btn:
            above all_filter-2 ~35px
            aligned horizontally all js-login-btn
            text is "Sign Up"
            css background-color is "rgba(93, 213, 115, 1)"
# check the default number of lessons displayed are matching are greater than 11
    global:
      count visible ml_lesson_card-* is > 11



