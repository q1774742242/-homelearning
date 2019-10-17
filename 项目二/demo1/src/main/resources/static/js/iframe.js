
$(function(){
	
	 setActiveIframeHeight();
	 setActiveIframeHeight.isFirst  = 1;
	
	
});
function setActiveIframeHeight(){ 
    //计算iframe内容的高度
　　function getBodyHeight(){ 
　　　　var height = 0; 
　　　　if (document) { 
　　　　　　height = $(document.body).height();//Math.max(document.body.clientHeight,document.body.offsetHeight); 
　　　　　　//获取iframe中显示的脱离文档流的元素
　　　　　　var panels = $('.panel-body'),
　　　　　　pHeight = 0; 
　　　　　　//计算其中最大的值
　　　　　　for(var i = 0; i < panels.length; i++){
　　　　　　　　//计算撑开iframe的高度
　　　　　　　　var panelContent = $(panels[i]), 
　　　　　　　　panelContentHeight = panelContent.height() + panelContent.offset().top + 50; 
　　　　　　　　pHeight = (panelContentHeight > pHeight)?panelContentHeight:pHeight; 
　　　　　　} 
　　　　　　height = (pHeight > height)?pHeight:height; 
　　　　} 
　　　　return height; 
　　} 
　　var curHeight = getBodyHeight(),
　　//这里使用#right-content-test自适应来探测中部内容显示区域的最小高度
　　minHeight = top.$('.Conframe').height(),
    //获取iframe元素     
　　htmlDom = top.$('.Conframe').find('iframe')[0]; 
　　curHeight = (minHeight >= curHeight) ? minHeight : curHeight; 
    
   //top.activeIframeHeight记录了当前的iframe的的高度     
　　if(htmlDom && htmlDom.height != top.activeIframeHeight){ 
　　　　htmlDom.height = top.activeIframeHeight; 
　　} 
         
　　//防止临界值导致滚动条时有时无使用Math.abs处理 
　　if(setActiveIframeHeight.isFirst || (Math.abs(top.activeIframeHeight - curHeight) > 2)){ 
　　　　top.activeIframeHeight = curHeight; 
　　　　htmlDom && (htmlDom.height = top.activeIframeHeight); 
　　} 
　　setActiveIframeHeight.isFirst = 0; 
}
