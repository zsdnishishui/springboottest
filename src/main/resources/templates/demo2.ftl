<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        body {
            font-family: SimHei;
        }

        .color {
            color: green;
        }

        .pos {
            position: absolute;
            left: 200px;
            top: 5px;
            width: 200px;
            font-size: 10px;
        }

        @media print {
            div.header-right {
                display: block;
                position: running(header-right);
            }
        }

        @page {
            size: 8.5in 11in;

            @top-right {
                content: element(header-right)
            };

            /*@bottom-center {
                content : "Page " counter(page) " of " counter(pages);
            };	 */
            @bottom-center {
                content: element(footer)
            }
        }

        #footer {
            position: running(footer);
        }

        #pages:before {
            content: counter(page);
        }

        #pages:after {
            content: counter(pages);
        }

    </style>
</head>
<body>

<div id="footer">
    <div style="text-align: center; width: 100%;font-size: 15px;">Page <span id="pages"> of </span></div>
</div>
<div class="page">
    <div class="color">你好，${name}222</div>
    <#--    <img src="data:${fileType};base64,${file64Str}" width="600px"/>-->
    <img src="https://q4.itc.cn/q_70/images03/20240405/39ec09deda3a41d79e03897b0fdf68a0.jpeg" width="600px"/>
    <img src="../static/imgs/582346.jpg" width="600px"/>
</div>

</body>
</html>