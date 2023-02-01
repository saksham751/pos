<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple"
                                       page-height="8.5in" page-width="11in" margin-top=".5in"
                                       margin-bottom=".5in" margin-left=".5in" margin-right=".5in">
                    <fo:region-body margin-top="2cm" margin-bottom="2cm" />
                    <fo:region-before extent="2cm" overflow="hidden" />
                    <fo:region-after extent="1cm" overflow="hidden" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple"
                              initial-page-number="1">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:apply-templates select="invoice" />
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <xsl:attribute-set name="tableAttributes">
<!--        <xsl:attribute name="border">solid 0.1mm #82CAFF</xsl:attribute>-->

    </xsl:attribute-set>
    <xsl:template match="invoice">
        <fo:block text-align="center">
            <fo:table table-layout="fixed" width="100%">
                <fo:table-column column-width="50%" />
                <fo:table-column column-width="50%" />
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-size="12pt" font-family="monospace"
                                      color="black" text-align="left" padding-top="3pt">
                                Date :
                                <xsl:value-of select="date" />
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
            <fo:table table-layout="fixed" width="100%">
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell padding-top="30pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="center"
                                      padding-top="3pt">
                                S. No.
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="30pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="center"
                                      padding-top="3pt">
                                Name
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="30pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="center"
                                      padding-top="3pt">
                                Quantity
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="30pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="center"
                                      padding-top="3pt">
                                Selling Price
                            </fo:block>
                        </fo:table-cell>
<!--                        <fo:table-cell padding-top="30pt">-->
<!--                            <fo:block font-size="15pt" font-family="monospace"-->
<!--                                      background-color="#82CAFF" color="black" text-align="center"-->
<!--                                      padding-top="3pt">-->
<!--                                Total-->
<!--                            </fo:block>-->
<!--                        </fo:table-cell>-->
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
            <fo:table table-layout="fixed" width="100%">
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-column column-width="25%" />
                <fo:table-body>
                    <fo:table-row>
                        <xsl:if test="position() mod 2">
                            <xsl:attribute name="background-color">
                                <xsl:text>#EEF0F2</xsl:text>
                            </xsl:attribute>
                        </xsl:if>
                        <fo:table-cell border-style="solid"
                                       border-width="1.0pt">
                            <xsl:apply-templates select="item/id" />
                        </fo:table-cell>
                        <fo:table-cell border-style="solid"
                                       border-width="1.0pt">
                            <xsl:apply-templates select="item/name" />
                        </fo:table-cell>
                        <fo:table-cell border-style="solid"
                                       border-width="1.0pt">
                            <xsl:apply-templates select="item/quantity" />
                        </fo:table-cell>
                        <fo:table-cell border-style="solid"
                                       border-width="1.0pt">
                            <xsl:apply-templates select="item/sellingPrice" />
                        </fo:table-cell>
<!--                        <fo:table-cell border-style="solid"-->
<!--                                       border-width="1.0pt">-->
<!--                            <xsl:apply-templates select="item/cost" />-->
<!--                        </fo:table-cell>-->
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
            <fo:table table-layout="fixed" width="30%" text-align="right">
                <fo:table-column column-width="50%" />
                <fo:table-column column-width="50%" />
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell padding-top="50pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="right" >
                                Total Amount
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-top="50pt">
                            <fo:block font-size="15pt" font-family="monospace"
                                      color="black" text-align="right">
                                <xsl:value-of select="total" />
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
    <xsl:template match="id">
        <fo:block font-size="12pt" font-family="monospace"
                  space-after.optimum="3pt" text-align="center" padding="3pt">
            <xsl:value-of select="." />
        </fo:block>
    </xsl:template>
    <xsl:template match="name">
        <fo:block font-size="12pt" font-family="monospace"
                  space-after.optimum="3pt" text-align="center" padding="3pt">
            <xsl:value-of select="." />
        </fo:block>
    </xsl:template>
    <xsl:template match="quantity">
        <fo:block font-size="12pt" font-family="monospace"
                  space-after.optimum="3pt" text-align="center" padding="3pt">
            <xsl:value-of select="." />
        </fo:block>
    </xsl:template>
    <xsl:template match="sellingPrice">
        <fo:block font-size="12pt" font-family="monospace"
                  space-after.optimum="3pt" text-align="center" padding="3pt">
            <xsl:value-of select="." />
        </fo:block>
    </xsl:template>
<!--    <xsl:template match="cost">-->
<!--        <fo:block font-size="12pt" font-family="monospace"-->
<!--                  space-after.optimum="3pt" text-align="center" padding="3pt">-->
<!--            <xsl:value-of select="." />-->
<!--        </fo:block>-->
<!--    </xsl:template>-->
</xsl:stylesheet>