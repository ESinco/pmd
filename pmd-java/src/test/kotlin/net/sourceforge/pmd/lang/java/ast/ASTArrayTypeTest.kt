package net.sourceforge.pmd.lang.java.ast

import net.sourceforge.pmd.lang.ast.test.shouldBe


/**
 * @author Clément Fournier
 * @since 7.0.0
 */
class ASTArrayTypeTest : ParserTestSpec({

    parserTest("Multi-Dim Array") {
        inContext(TypeParsingCtx) {

            "ArrayTypes[][][]" should parseAs {

                arrayType {

                    it::getElementType shouldBe classType("ArrayTypes")

                    it::getDimensions shouldBe child {
                        arrayDim { }
                        arrayDim { }
                        arrayDim { }
                    }
                }
            }
        }
    }

    parserTest("Annotated array type") {
        inContext(TypeParsingCtx) {

            "ArrayTypes[][] @A []" should parseAs {

                arrayType {
                    it::getElementType shouldBe classType("ArrayTypes")

                    it::getDeclaredAnnotations shouldBe fromChild<ASTArrayDimensions, List<ASTAnnotation>> {

                        arrayDim { }
                        arrayDim { }
                        fromChild<ASTArrayTypeDim, List<ASTAnnotation>> {

                            val lst = listOf(annotation("A"))

                            it::getDeclaredAnnotations shouldBe lst

                            lst
                        }
                    }
                }
            }
        }
    }

    parserTest("Multi-Dim Array allocation") {
        inContext(ExpressionParsingCtx) {
            "new ArrayTypes[][][] { }" should parseAs {

                child<ASTArrayAllocation> {

                    arrayType({
                        classType("ArrayTypes")
                    }) {
                        arrayDim {  }
                        arrayDim {  }
                        arrayDim {  }
                    }

                    arrayInitializer { }
                }
            }
        }
    }
})
