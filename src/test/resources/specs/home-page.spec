# Check the layout of objects on home page
@objects
    ml_logo                 css     .ml-logo
    ml_navigation_section   css     .ml-navigation-section
    ml-header               id      ml-header
    ml_lesson_card-*        css     .ml-lesson-card
    ml-header-search        css     .ml-header-search
    ml-login                css     .ml-login
    js-login-btn            css     .js-login-btn
    js-signup-btn           css     .js-signup-btn
    dl_filter_all           css     .dl-filter-all
    dl_filter_beginner      css     .dl-filter-beginner
    dl_filter_intermediate  css     .dl-filter-intermediate
    dl_filter_expert        css     .dl-filter-expert

= Header =
# check ML logo is at top left corner of the screen
        ml_logo:
            inside  screen  0px top left
# check the navigation section is right of the logo and text is Explore All Lessons
        ml_navigation_section:
            right-of ml_logo
            text is "Explore All Lessons"
# check the header image
#    ml-header:
#        image file dump/homepage/objects/ml-header.png,error 4%
# check search box is inside the header section and right of navigation section
        ml-header-search:
            inside  ml-header
            right-of    ml_navigation_section
# check sign up and login button are inside header and right of search box
        ml-login:
            inside ml-header
            right-of ml-header-search
# check sign up and log in buttons are aligned horizontally
        js-signup-btn:
            aligned horizontally all js-login-btn
# check header contains logo, navigation , search box and login buttons
        ml-header:
            contains ml_logo, ml_navigation_section, ml-header-search, js-signup-btn, js-login-btn
= Body =
    @on *
# check the default number of lessons displayed are matching with 12
    global:
        count any ml_lesson_card-* is 12
# check all filters are aligned horzontally
    dl_filter_all,dl_filter_beginner, dl_filter_intermediate:
        aligned horizontally all  dl_filter_expert
    js-signup-btn:
        css background-color is "rgba(93, 213, 115, 1)"