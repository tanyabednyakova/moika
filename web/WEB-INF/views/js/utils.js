/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта
 *
 * @since 2017-03-02
 * @author Kovalev Aleksandr
 */


/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта
 *
 * @param toURL - адрес куда отсылать проверяемое значение
 * @param selector - Селектор input элемента сожержимое которого надо проверять
 */
function setActiveInput(toURL, selector){
    $(selector).change(function(){
        var value = $(this).val();
        $.ajax({
            method:"GET",
            url:toURL+value,
            success:function(data){

            }
        });
    });
}