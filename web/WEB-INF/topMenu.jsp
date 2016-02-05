<%-- 
    Document   : TopMenu
    Created on : 2 févr. 2016, 20:23:29
    Author     : Niels
--%>
<div class="title-bar" data-responsive-toggle="example-menu" data-hide-for="medium">
  <button class="menu-icon" type="button" data-toggle></button>
  <div class="title-bar-title">Menu</div>
</div>

<div class="top-bar" id="example-menu">
    <div class="top-bar-left">
    <ul class="dropdown menu" data-dropdown-menu>
        <li class="menu-text">${topMenuName}</li>
        <li><a href="#">Sentiment analysis</a>
            <ul class="menu vertical">
                <li><a href="#">Tweets</a></li>
                <li><a href="#">movie reviews</a></li>
                <li><a href="#">book reviews</a></li>
                <li><a href="#">parliamentary debates</a></li>
            </ul>
        </li>
        <li><a href="#">Help</a></li>
    </ul>
    </div>
    <div class="top-bar-right">
        <button type="button" class="success button">Log on</button>
        <button class="button" type="button" data-toggle="example-dropdown">log in</button>   
    </div>
    <ul class="menu">
        <li><input type="search" placeholder="Search"></li>
        <li><button type="button" class="button">Search</button></li>
    </ul>
</div>
    